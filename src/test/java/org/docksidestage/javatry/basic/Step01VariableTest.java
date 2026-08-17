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

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author kotaro.kohama
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8null:mai

        // kohama mystic8:mai だと思った。文字列の結合をしていそうなのに、nullは "null" になるらしい。
        // done kohama [ふぉろー] C#とかだと空文字になるので合ってて、Javaだと "null" に by jflute (2026/07/16)
        // 画面やメール文言に null って出てきてしまう問題が発生しやすい一方で、
        // ログで画面でも "null" って出てくるから開発時はわかりやすいという面も。
        // 細かいですが、どっちもどっちという感じではあります。
        // #1on1: nullのネタ話、メリデメの話 (2026/07/17)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic"; // 1丁目1番地
        String land = "oneman"; // 2丁目2番地
        sea = land; // seaの中に入っている紙(1丁目1番地)を、2丁目2番地に書き換えた
        land = land + "'s dreams"; // 4丁目4番地に "oneman's dreams" ('s dreams は3丁目だけど誰からも参照されない)
        log(sea); // your answer? => oneman

        // kohama 参照渡しじゃなくてよかった
        // done kohama [ふぉろー] seaとlandが一瞬同じアドレスで同じインスタンスを指し示しますが... by jflute (2026/07/16)
        // land = land + "'s dreams" で新たに作られた文字列を指し示すことになります。
        // でも、seaが持つアドレスと差し示しているインスタンスは何も変わってないのでそのままということですね。

        // done jflute 1on1にて、変数とインスタンスのお話をする予定 (2026/07/16)
        // ↑このとぅどぅはくぼ用のとぅどぅなのでそのまま残しておいてもらえればと。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415

        // #1on1: プリミティブ型は値そのものが変数に格納されるイメージ (2026/07/17)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1)); // seaに416へのアドレス
        sea.add(new BigDecimal(1)); // 417をnewしたけど、そのインスタンス無視
        log(sea); // your answer? => 416

        // kohama 417じゃないのか...。いきなり何もわからない。new はインスタンスを作っていて、BigDecimal はオリジナルのクラス？
        //        sea.add(...) で自身を書き換えるというわけではなさそう。imutable？
        // done kohama [ふぉろー] newはインスタンスを作っているでOKです by jflute (2026/07/16)
        // BigDecimal は、Java標準で提供されているクラスではありますが、まあ普通のクラスと捉えてOKです。
        // add()が何をしているのか？そこがポイントですよね。immutableというキーワード良いですね！

        // done jflute 1on1で、immutableのお話する予定 (2026/07/16)
        // #1on1: まず、Immutableなクラスかどうかの見極め方 (2026/07/17)
        // add()のソースコードリーディングもしてみた。JavaDocも紹介。
        // #1on1: immutableとmutable, メリデメ (2026/07/17)
        // immutableのメリット: 書き換えられないから安全/安心
        // immutableのデメリット: インスタンス多くなる、sea.add()のケース
        // mutableのメリット: いちいちインスタンス作らない、書くのが簡単になりやすい
        // mutableのデメリット: 書き換えられちゃうから不安
        //
        // immutableなやり方が徐々に好まれるようになってきた。
        // どこまで徹底するかは、言語の文化、組織の文化次第。
        //
        // sea.add()で自分が変わらないの気持ち悪い(笑) by こはまさん

        // #1on1: この業界の新しい/古いの話 (2026/07/17)
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    // #1on1: インスタンス変数とは？ (2026/08/07)
    // インスタンスの中に入ってる変数 → インスタンスに属している変数
    // (前回、メンバー変数とインスタンス変数の話をした by こはまさん)
    // BigDecimalとStringのインスタンス変数のコード読んでみた。

    // テスト実行時のエディター内でのコードイメージ:
    //  Step01VariableTest variableTest = new Step01VariableTest();
    //  variableTest.test_variable_basic();

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => null

        // kohama instanceBroadway はインスタンスのアドレスが格納される変数。"String instanceBroadway" だけだとヌルポっぽい。
        // #1on1: 変数のアドレスの話を振り返り。 (2026/08/07)
        // ヌルポ: NullPointerException
        // instanceBroadway.toLowerCase() みたいに、nullの変数に対して、無理やりメソッド呼び出しすると発生。
        // 実行時例外と呼ぶ。処理が中断してしまいました。
        // プログラミング言語の決め、Javaの場合、これはほぼバグでしょうということで、中断するようにした。

        // 白紙の紙を渡す(nullを渡す)のは大丈夫なんだけど、
        // 白紙の紙の状態でメソッドを呼ぶのはダメ。(by こはまさん)
        // nullの例外に関しては、step7とかでまたじっくりやります (by jflute)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0

        // kohama primitive な型なので、変数に値が直接格納される。宣言時に何も代入していないので、中身は未定義ということになるが、まあ0になりそうだと思った。
        // done kohama [いいね] そのとおり、コンパイルエラーになってないということは何かしらのデフォルト値が入るということですね。 by jflute (2026/08/05)
        // 一方で、ローカル変数だと、中身が未定義で使用するとコンパイルエラーになります。
        // e.g.
        //  int land;
        //  log(land); // コンパイルエラー
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null

        // kohama primitive な int と違って、Integer はオブジェクト（？）なので、Stringの時と同じ挙動だと思った。
        // done kohama [ふぉろー] オブジェクトで合ってます。プリミティブ型に対してオブジェクト型と呼びます。 by jflute (2026/08/05)
        // (違う視点の言葉で、Integerはラッパー型とも呼ばれます)
        //
        // オブジェクト型: プリミティブ型以外のデータ型 (クラスになってるもの) 全部
        //  e.g. String,Integer,Step01VariableTest
        //
        // ラッパー型: プリミティブ型(8個)にそれぞれ対応するオブジェクト型(8個)
        //  e.g. Integer, Long, Boolean, Character... (8個)
        //       int      long  boolean  char
        //
        // 実は、String や BigDecimal はラッパー型ではない。
        // ラッピングはしてるけど、文法上のラッパー型というわけではない。
        //
        // 独自のintのラッパークラスを作ることはできる？ by こはまさん
        // yes, ただ、Javaから特別扱いされないただのクラスになるので...
        // KohaInt koha = new KohaInt(99); // 99を直接代入できない。
        // KohaInt hyakuKoha = koha + 1;   // これもできない。"+" が利かない。
        // まさしく、BigDecimalがこれ (かわいそう)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1|null|magician

        // kohama 整理：String は immutable だが、新しいインスタンス "bbb" を作って、そのアドレスを instanceBroadway に代入することは可能。
        //        test関数からヘルパー関数へは、中身が "magician" のインスタンスのアドレスが渡される。そのアドレスを、ヘルパー関数が独自で宣言した instanceMagiclamp に代入。
        //        ヘルパー側の instanceMagiclamp に新しく、 中身が "burn" のインスタンスのアドレスを代入しても、test関数には影響なし！
        // done kohama [いいね] アドレスの旅のイメージしっかりできてますね！ by jflute (2026/08/05)
        // メソッド呼び出しは、変数という箱そのものが引き渡されるわけではなく、その中身が引き渡されますので、
        // 今回たまたま同名の別の変数(別の箱)と言えます。

        // done jflute 1on1にて、インスタンス変数を改めて深掘り (2026/08/05)

        // #1on1: ローカル変数とインスタンス変数の寿命 (Lifecycle) (2026/08/07)
        // ローカル変数は、基本的に短命。メソッド実行時に確保されて、終わったらすぐに破棄される。
        // インスタンス変数は、インスタンスの寿命と一致する。
        // 短命な時もあれば、長寿のときもある。

        // 質問: インスタンスが消える時って？
        // 参照されなくなったら、ガベージ対象になって、そのうちガベージコレクションで破棄される。
        // C言語みたいに、自分で明示的にfreeはしない。
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor

        // kohama sea.concat() は、結合した文字列で新しいインスタンスを作ってreturnする人。
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor416

        // kohama Mutable Method-callって書いてあるから、こうなりそうなんだけど、理由は...。
        // StringBuilderのappendは、AbstractStringBuilderのappendをオーバーライドしてるぽい。
        // append(long i)は、結局その処理をappend(String str)に投げている。
        // append(String str)で、countは += len されているけど、valueは...？
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor

        // kohama ヘルパー関数の中で sea = new StringBuilderしても、テスト関数のseaは変わらない。
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */

    private int piari;

    public void test_variable_writing() {
        String sea = "mystic";
        Integer land = null;
        log(sea + ", " + land + ", " + piari);

        // kohama piari に private 修飾子を付けなくても動いた。そらそうか。
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * メソッド終了時の変数 sea の中身は？
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        String sea = "biz";
        String land = "reach";
        land = sea;
        sea = land;
        log(sea); // your answer? => biz

        // kohama swapに失敗したプログラマみたいなエクササイズ
    }
}
