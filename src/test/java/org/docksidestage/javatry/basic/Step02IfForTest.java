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
package org.docksidestage.javatry.basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author kotaro.kohama
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
            sea = sea++ * 2;
        } else if (land && sea >= 904) {
            sea = 7;
            sea = ++sea * 2;
        } else if (sea >= 903 || land) { // ここ
            if (sea % 2 == 0) { // true
                sea = sea++ * 2; // sea = 1809 // $掛け算の後、足されたと思った？
                log("@@@: " + sea); // 1808 (++してもしなくても)
            }
            if (!land) { // true
                land = true; // land = true なのでここを通ればseaは10
            } else if (sea <= 903) {
                sea++;
            }
            if (sea < 1810) { // true
                sea = 8; // sea = 8
            }
        } else if (sea == 8) { // 入らない
            sea++;
            land = false;
        } else { // 入らない
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) { // 読まなくていい
            sea--;
            if (sea % 2 == 1) {
                sea++;
            }
        }
        if (land) { // 入る
            sea = 10; // sea = 10
        }
        log(sea); // your answer? => 10

        // kohama: 誰だこんなコードを書いたのは！！
        // sea = sea++ * 2 は 1908 になるらしい。後置インクリメントだから、式全体を評価してから (sea * 2 で 1908) になってからインクリメントかと。
        // #1on1: ここはJavaの罠なところで、実際はみんな式の途中でインクリメントは使わないようにしている (2026/09/04)
        // 厳密には前置だったら大丈夫とかあるけど、その棲み分けを覚えておくのも面倒。
        // jfluteは、単独行でしかインクリメントは使わないようにしている。
        // かつ、前置がほとんど。二重の防波堤で落とし穴に落ちないようにしている。
        // 「誰だこんなコードを書いたのは！！」は正しい。
        //
        // #1on1: 結局10, やっぱり誰だこんなコードを書いたのは！！が再び (2026/09/04)
        // javatryとしては、ifのトレーニングなので地道に読んでもらってGood。
        //
        // ソースコードリーディングのコツ「漠然読み」
        // 
        // (スクロールして輪郭だけ見る)
        // o 漠然読みで構造把握 (全体像を見る)
        //  → 変数宣言、大中小のif, ログ出しの5つパート
        //
        // o 当たりを付けてフォーカス読み
        //  → 目的に沿って当たりを探す、seaから逆さ読みでsea=10を見つける
        //  → or 全体像を把握しているので、自然とsea=10が目に入りやすい
        // 
        // 最初から当たりを見つけに行く読み方。(裏ルートがあること前提で読む)
        // 
        // 当然、ギャンブルに負けることはあります。でも、損はないという考え方。
        // 構造把握してて、ある程度踏み込んでるので、０から網羅読みするよりは速く読めるようになってる。
        // (頭の中で地図があって現在地がわかる状態で読む方が安定する)
        //
        // あとは、次の当たりが見つかることもある。
        // 3,4回繰り返しても、０から網羅読みするよりは速い可能性。
        // 
        // よもやま: 仮説思考的なコードリーディング!?
        // 
        // done kohama [読み物課題] My Favorite Book: 仮説思考 by jflute (2026/09/04)
        // https://jflute.hatenadiary.jp/entry/20150111/kasetsu
        //
        // done kohama [読み物課題] jfluteのプログラマーオススメ五冊 by jflute (2026/09/04)
        // https://jflute.hatenadiary.jp/entry/20150727/fivebooks
    }

    // done jflute 次回1on1はforから (2026/09/04)
    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList(); // [ "broadway", "dockside", "hanger", "magiclamp" ]
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList(); // [ "broadway", "dockside", "hanger", "magiclamp" ]
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp

        // kohama: どえー、Javaにも範囲forあるんですね。

        // #1on1: Javaの文法としてのループ2つ (2026/09/18)
        // o intあいのfor文: Java当初から (1995年)
        // o 拡張for文(foreach文): Java10年目くらいから (2005年)
        //
        // intあいのfor文は毎回探しにいく。
        // foreach文は次の方どうぞ方式。
        //
        // 回す方式が違う。
        //
        // リストが ArrayList の場合、どっちにせよ配列添字アクセスで速い。
        // リストが LinkedList の場合、get(i)だと内部ループで探しちゃう。
        //
        // LinkedListの使い所は？
        // A, B, C のリストに対して、A, A', B, C と 途中にA'を追加する時。
        // LinkedListならズレるとかがないので、速い。(更新が速い)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList(); // [ "broadway", "dockside", "hanger", "magiclamp" ]
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hanger
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList(); // [ "broadway", "dockside", "hanger", "magiclamp" ]
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => dockside

        // kohama: functional programmingってやつかも。
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    public void test_iffor_making() {
        List<String> stageList = prepareStageList();
        // A: 所属は test_メソッド
        for (String stage : stageList) {
            // B: 所属は test_メソッド
            if (stage.contains("a")) {
                log(stage);
            }
        }

        //        stageList.forEach(stage -> {
        //            if (stage.contains("a")) {
        //                log(stage);
        //            }
        //        });

        // kohama: 素直が一番。
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        // done kohama stageListの内容が変わる想定でも互換性を保つようにしてみましょう by jflute (2026/09/04)
        // e.g. hangarが存在しない時、bongarという新しい要素が追加された時、stageList空っぽのとき
        // でも、前のプログラムと同じ結果になるように。
        List<String> stageList = prepareStageList();

        // #1on1: $ Stack は使うなと言われたから Deque (2026/09/18)
        // 他とか、StringBuilder とか new String[1] わりと多い。
        // Stack と Deque の違い。synchronized のロックコストのお話。
        // 素晴らしい、しっかり配慮できている。
        Deque<String> seaDeque = new ArrayDeque<>();
        // A: 所属は test_メソッド
        stageList.forEach(stage -> {
            // B: 所属は -> {} (AbcConsumerのaccept())
            // なので、{} 内は別クラス別メソッド
            if (seaDeque.peek() != null && seaDeque.peek().contains("ga")) {
                return;
            }
            if (!stage.startsWith("br")) {
                seaDeque.push(stage);
            }
        });
        log(seaDeque.peek());
    }
    // #1on1: 外側のローカル変数の再代入が -> {} の中でできない理由は？ (2026/09/18)
    // $forEach()メソッドは、場合によっては順番がバラバラになる可能性？
    //
    // そもそもforEach()メソッドの仕組み。
    // $おーーーーーーーー、ArrayListにやってといてと渡してるだけだから...
    // forEach()メソッドのコード読んで見ると、ただのforの代理人。
    // ただのメソッド。コンパイラーからすると、ループかどうかわかってない。
    // -> {} (Lambda式) は、実は単なるクラス宣言＆newインスタンスしてる記法。
    // イメージ new AbcConsumer.accept() { ってやっているようなもの。
    //
    // 別クラス別メソッドで、外側のローカル変数を書き換えるってのはできなさそう。
    // ローカル変数のコンセプトからしたら、ローカルの外に行っちゃってて...
    // もし仮に書き換えることができるようになってたとしたら...
    // 時系列的なカオスを生みやすくなる。時系列的な矛盾も発生する。
    // なので、Javaではできなくしてる。ローカル変数のコンセプトを維持するために。
    //
    // but 固定の変数 (immutableな変数) であれば、参照はできる。(副作用も発生しにくでしょうと)
    // mutableな変数だと、Lambda式から見て、時系列的な偶然性に依存することになる。
    //
    // なので、seaDeque の変数の指し示す先を変えることはできないけど、
    // 指し示す先のインスタンスの中で保持されている変数はご自由に。(mutableクラスなら)
    //
    // forEach()メソッド: できないことだらけのループ (外側変数書き換えダメ、continue/break使えない)
    // ただ、Java20年目くらいから (2015年くらいから) に登場したわけだけど...
    // 後から出てきて、意味のないもの追加するわけないので、なにか存在意義があるはず。

    // TODO jflute forEach()メソッドの存在意義は？ (2026/09/18)

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * prepareCharacterList()が返す文字列のリストの中から、
     * 先頭の文字と末尾の文字が一致しているものだけを出力してください。
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        List<String> characterList = prepareCharacterList();
        characterList.forEach(character -> {
            if (character.charAt(0) == character.charAt(character.length() - 1)) {
                log(character);
            }
        });
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }

    private List<String> prepareCharacterList() {
        // #1on1: いいね、名前がいい (2026/09/18)
        List<String> characterList = new ArrayList<>();
        characterList.add("mickey");
        characterList.add("donald");
        characterList.add("eeyore");
        characterList.add("tiger");
        characterList.add("goofy");
        return characterList;
    }

    // kohama: ArrayList<>()を読み解きたい気持ち、あります
    // List は Collection を継承？したインターフェースで、ArrayList は List を実装したクラス。
}
