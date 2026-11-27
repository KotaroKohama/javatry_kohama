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

/**
 * @author jflute
 * @author kohama
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #1on1: いいね、remainingという言葉がわかりやすい (2026/11/27)
    // 残り日数なのか、入った日数なのかの区別が必要だが、しっかりできている。
    // Entries で入園回数というint概念を示している。
    // Days で日数ってint概念を示すのが一般的なので、そのルールでいうと悪くない。
    // Times もあるか。
    // $remainingParkEntryDaysだったら、誤解は少ないかも!?
    // yes
    private final int displayPrice; // written on ticket, park guest can watch this
    private int remainingParkEntries;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int remainingParkEntries) {
        this.displayPrice = displayPrice;
        this.remainingParkEntries = remainingParkEntries;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (remainingParkEntries <= 0) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        remainingParkEntries -= 1;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    // #1on1: isAlreadyIn()を1回以上のニュアンスで見てるのか、使い切りで見てるのか？ (2026/11/27)
    // 新機能追加したときに、既存のプログラムの概念が破綻することがよくある。
    // 新機能追加だからといって油断しない。
    //public boolean isAlreadyIn() {
    //    return 
    //}

    public int getDisplayPrice() {
        return displayPrice;
    }

    public int getRemainingParkEntries() {
        return remainingParkEntries;
    }

    public boolean hasRemainingEntries() {
        return (remainingParkEntries > 0);
    }
}
