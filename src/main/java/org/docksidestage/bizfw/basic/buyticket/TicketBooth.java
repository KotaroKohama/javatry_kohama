/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

import java.util.HashMap;
import java.util.Map;

// TODO kohama authorいれておいてください by jflute (2026/11/06)

/**
 * @author jflute
 * @author kohama
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200; // $Geminiさんが調べてくれた
    // $年パスなくなった

    private enum TicketType {
        ONE_DAY, TWO_DAY
    }

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #1on1: いいね、在庫を分けました！ (2026/11/06)
    // $迷いました！
    // 迷うが正解。紙のイメージトレーニング。
    // TODO kohama 名前、...Quantityだと単体の値に見えちゃうので... by jflute (2026/11/27)
    // e.g. passportQuantities or passportQuantityMap
    // #1on1: 変数名に型の情報をどの程度入れるか？ (2026/11/27)
    // o 必ず入れる e.g. salesProceedsInt, intSalesProceeds
    // o 絶対入れない e.g. passportQuantities
    // $絶対入れないってほどではないけど、ここだとpassportQuantitiesかなぁ。
    // これは 0/1 なのか？真ん中付近があるのか？
    // booleanの変数名/メソッド名ってどうしている？
    // $is, hasとか
    // e.g.
    //  boolean prepared; boolean isPrepared;
    //  boolean deleted; boolean isDeleted;
    // has, exists, needs... // 三単現の活用の動詞
    // isShow... or shows... or show... (or isShown: 表示されてるかどうか？)
    // or needsShowing... or canShow...
    // may, can, should, must, ...
    //
    // booleanは、booleanっぽさを一生懸命表現しようとするのがわりと一般的。
    // そういう意味では、型の匂わせは多くの人がやってることなのかな!?
    //
    // jfluteの場合、Collectionに関しては使い方をパット見で区別したいのでMapってよく使う。
    // でもそれが一般的というわけではない。
    //
    private final Map<TicketType, Integer> passportQuantity = new HashMap<TicketType, Integer>();
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
        // TODO kohama チケット種別が増えても追加修正しなくても済むようにしてみましょう by jflute (2026/11/27)
        // (すべてのチケット種別が全部同じMAX_QUANTITYであることが前提)
        passportQuantity.put(TicketType.ONE_DAY, MAX_QUANTITY);
        passportQuantity.put(TicketType.TWO_DAY, MAX_QUANTITY);
        salesProceeds = 0;
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return チケット (Ticketクラス）
     */
    public Ticket buyOneDayPassport(Integer handedMoney) {
        // TODO done kohama 修行++: 最終的に、QuantityのoneDayの登場が1回になるようにしてみましょう by jflute (2026/11/06)
        // $イメージできてるかも
        doBuyPassport(handedMoney, TicketType.ONE_DAY);
        return new Ticket(ONE_DAY_PRICE, 1);
    }

    // #1on1: JavaDocコメント、twoDay入れてくれてGood (2026/11/06)
    // done kohama JavaDoc, @returnを入れておきましょう (日本語でOK) by jflute (2026/11/06)
    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     * @return チケット購入結果（Ticketオブジェクト、お釣り）
     */
    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        Integer change = doBuyPassport(handedMoney, TicketType.TWO_DAY);
        Ticket ticket = new Ticket(TWO_DAY_PRICE, 2);
        return new TicketBuyResult(ticket, change);
    }

    // done kohama アドバンス: publicのbuyとprivateのbuyを区別するテクニック by jflute (2026/11/06)
    // メソッド一覧とかで紛れたり、会話上「buyメソッド」でどっち？ってなりやすいので...
    // e.g. doBuyPassport(), internalBuyPassport()
    private Integer doBuyPassport(Integer handedMoney, TicketType ticketType) {
        // #1on1: チケット種別が増えたときに頑張ってもらう (2026/11/27)
        Integer price = ticketType == TicketType.ONE_DAY ? ONE_DAY_PRICE : TWO_DAY_PRICE;
        Integer quantity = passportQuantity.get(ticketType);
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short of money. Required " + price + ", but handed " + handedMoney);
        }
        salesProceeds = salesProceeds + price;
        passportQuantity.put(ticketType, quantity - 1);
        Integer change = handedMoney - price;
        return change;
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // #1on1: いいね、元々が getQuantity() というシンプルなメソッド名だったけど、変更してわかりやすい (2026/11/06) 
    // OneDayのみ時代から、One/TwoDay時代に変わったので、getQuantity()という名前が曖昧になる。
    // OneDayのみ時代: OneDayの在庫なのか？トータル在庫なのか？が同じだった時代
    // (概念の分離をできること自体が大事)
    public int getOneDayPassportQuantity() {
        return passportQuantity.get(TicketType.ONE_DAY);
    }

    public int getTwoDayPassportQuantity() {
        return passportQuantity.get(TicketType.TWO_DAY);
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
