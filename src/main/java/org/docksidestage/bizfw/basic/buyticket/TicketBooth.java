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

// TODO kohama authorいれておいてください by jflute (2026/11/06)
/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200; // $Geminiさんが調べてくれた
    // $年パスなくなった

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #1on1: いいね、在庫を分けました！ (2026/11/06)
    // $迷いました！
    // 迷うが正解。紙のイメージトレーニング。
    private int oneDayPassportQuantity = MAX_QUANTITY;
    private int twoDayPassportQuantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
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
     */
    public void buyOneDayPassport(Integer handedMoney) {
        // TODO kohama 修行++: 最終的に、QuantityのoneDayの登場が1回になるようにしてみましょう by jflute (2026/11/06)
        // $イメージできてるかも
        Integer change = buyPassport(handedMoney, ONE_DAY_PRICE, oneDayPassportQuantity);
        --oneDayPassportQuantity;
    }

    // #1on1: JavaDocコメント、twoDay入れてくれてGood (2026/11/06)
    // TODO kohama JavaDoc, @returnを入れておきましょう (日本語でOK) by jflute (2026/11/06)
    /**
     * Buy two-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public Integer buyTwoDayPassport(Integer handedMoney) {
        // TODO kohama 凡ミス, oneDay使ってる by jflute (2026/11/06)
        // #1on1: そもそもコピペを発生させない仕組みづくりは努力するとして... (2026/11/06)
        // 一方で、ぼくらはコピペが０になるわけではない。ので、コピペを正確にするスキルもあった方が良い。
        // 指差し確認が基本。エディターのテキスト操作を使うなど。新規ファイルで作業するなど。
        if (oneDayPassportQuantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < TWO_DAY_PRICE) {
            // #1on1: エラーメッセージがりっちでわかりやすくなってる (2026/11/06)
            // handedMoney をログに残しているのが素晴らしい。
            throw new TicketShortMoneyException("Short of money. Required " + TWO_DAY_PRICE + ", but handed " + handedMoney);
        }
        --twoDayPassportQuantity;
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + TWO_DAY_PRICE;
        } else {
            salesProceeds = TWO_DAY_PRICE;
        }
        return handedMoney - TWO_DAY_PRICE;
    }

    // TODO kohama アドバンス: publicのbuyとprivateのbuyを区別するテクニック by jflute (2026/11/06)
    // メソッド一覧とかで紛れたり、会話上「buyメソッド」でどっち？ってなりやすいので...
    // e.g. doBuyPassport(), internalBuyPassport()
    private Integer buyPassport(Integer handedMoney, Integer price, Integer quantity) {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
        if (handedMoney < price) {
            throw new TicketShortMoneyException("Short of money. Required " + price + ", but handed " + handedMoney);
        }
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }
        return handedMoney - price;
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
        return oneDayPassportQuantity;
    }

    public int getTwoDayPassportQuantity() {
        return twoDayPassportQuantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
