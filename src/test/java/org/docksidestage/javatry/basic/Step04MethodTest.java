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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author kohama
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? => over
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        // #1on1: Javaにおける関数という言葉 (2026/10/23)
        // 文法的には関数という言葉は基本出てこなくて、メソッドと呼ぶ。
        // 唯一、FunctionalInterfaceというアノテーションで関数という言葉が出てくる。
        // 関数型プログラミングのコンセプトを取り入れた機能で、関数という言葉を使ってる。
        // なのでC言語言うような関数のニュアンス関数は出てこない。
        // $関数の方が馴染みがある、メソッドはクラスの中の関数をメソッドと呼ぶイメージ
        //
        // 関数: 引数戻り値の世界
        // メソッド: 引数戻り値 + インスタンス内部の情報/状態
        //
        // メソッドも、オブジェクト指向としての概念的なメソッドという用語(広義)と、
        // Javaの文法用語としての呼び名としてのメソッドという用語(狭義)が、混じる。
        //
        // 関数も、数学の関数 or 関数型プログラミングの関数としての用語(広義)と、
        // 何言語の文法用語としての呼び名としての関数という用語(狭義)が、混じる。
        //
        // 概念用語と文法用語の違い。
        //
        String sea = functionSomething("mystic");
        consumeSomething(supplySomething());
        runnableSomething();
        log(sea); // your answer? => mysmys

        // #1on1: $Pythonは、関数の中に関数を作ることができて頭おかしい (2026/10/23)
        // Javaも負けてられない。メソッドの中に名前付きclassを作ることもできる。
        /*
        public void yyy() {
            class Xxxxx {
            public void xxxxxxxxxxx() {
            
            }
            }
            new Xxxxx().xxxxxxxxxxx();
        }
         */

        // #1on1: ちょこっとScalaのお話 (2026/10/23)
    }

    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    // #1on1: 引数なし戻り値ありのメソッドをSupplierと呼ぶ (2026/10/23)
    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    // #1on1: 引数あり戻り値なしのメソッドをConsumerと呼ぶ (2026/10/23)
    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable);
        if (!land) {
            sea = sea + mutable.getStageName().length();
        }
        log(sea); // your answer? => 904 -> 910
    }

    // kohama あれ。helloMutable に渡した mutable は、プリミティブ型じゃなくて、オブジェクト型だから、参照渡しみたいになるのか...。忘れてたし、厄介だ。

    // #1on1: 参照渡しのイメージ？ (2026/10/23)
    // $ "&を付けましょう" C言語のポインター。アドレスを渡していたら参照渡しのイメージ。
    // $自分へのアドレスを渡す。
    //
    // 本来の参照渡し: "&" で良い。変数自体のアドレス。
    // 本来の値渡し: intの7とか、インスタンスへのアドレスとか。(primitiveとobjectを区別してない)
    //
    // いまの参照渡し: インスタンスへのアドレスを渡してる
    // いまの値渡し: intの7とか具体的な値を渡してる
    //
    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {
            goToPark();
        }
        ++sea;
        sea = inParkCount;
        log(sea); // your answer? => 100
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // TODO jflute 次回この辺から (2026/10/23)
    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    private boolean availableLogging = true;

    public void test_method_making() {
        String replaced = replaceCwithB(replaceAwithB("ABC"));
        String sea = quote(replaced, "'");
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }

    // write methods here
    private String replaceAwithB(String str) {
        return str.replace("A", "B");
    }

    private String replaceCwithB(String str) {
        return str.replace("C", "B");
    }

    private String quote(String str, String quotation) {
        return quotation + str + quotation;
    }

    private boolean isAvailableLogging() {
        return availableLogging;
    }

    private void showSea(String sea) {
        log(sea);
    }
}
