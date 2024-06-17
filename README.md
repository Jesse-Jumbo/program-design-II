![螢幕擷取畫面 2024-06-04 193850](https://hackmd.io/_uploads/ByRC2_hEA.png)
# Program Design II

## 專案簡介
《PROGRAM DESIGN II》是由部政佑、江婕瀅、黃若慈自主研發的一款操作極簡的問答類戰鬥RPG。遊戲發生在一個被稱作「國立成功大學」的幻想世界。在這裡，被選中的人們將會被授予「Java大法」，獲得物件導向之力。你將扮演一位名為「大學生」的神秘角色，在自由的旅行中邂逅性格各異、能力獨特的同伴們，和他們一起擊敗強敵、找回失散的學分——與此同時，逐步發掘「程式設計二」的真相。

## 安裝步驟
1. 克隆這個倉庫到你的本地機器：
    ```sh
    git clone https://github.com/Jesse-Jumbo/Program-Design-II.git
    ```
2. 進入專案目錄：
    ```sh
    cd Program-Design-II
    ```
3. 使用 Gradle 建立專案：
    ```sh
    ./gradlew build
    ```
4. 運行遊戲：
    ```sh
    ./gradlew run
    ```

## 使用說明
### 如何使用這個遊戲
1. clone [此專案](https://github.com/Jesse-Jumbo/program-design-II)。
2. 參照安裝步驟進行安裝和運行。
3. 運行 Game.java 後，進入遊戲主畫面，點擊 "Start Game" 開始遊戲。
4. 按照劇情提示和關卡選擇進行遊戲。

### 基本操作
所有操作皆使用滑鼠左鍵點擊。

### 玩法說明
1. 進入遊戲後，點擊 "Start Game" 按鈕。
   <img src="https://raw.githubusercontent.com/Jesse-Jumbo/program-design-II/main/src/main/resources/assets/image/readme/main_menu.png" alt="Main Menu" width="1000"/>
2. 第一次遊玩會進入前情提要，根據劇情內容點擊滑鼠左鍵繼續。
   ![image](https://hackmd.io/_uploads/SJr3kF2V0.png)
3. 劇情結束後進入關卡選擇介面，分為五個 Chapter，每個 Chapter 有五個 Level。
   <img src="https://raw.githubusercontent.com/Jesse-Jumbo/program-design-II/main/src/main/resources/assets/image/readme/level.png" alt="Level View" width="1000"/>

4. 遊戲內會詢問與 PD2 課程內容相關的問題，有 A、B、C、D 四個選項。
   <img src="https://raw.githubusercontent.com/Jesse-Jumbo/program-design-II/main/src/main/resources/assets/image/readme/fight.png" alt="Fight View" width="1000"/>

5. 前四關需答對五題獲勝，失敗三題則遊戲結束。
   <img src="https://raw.githubusercontent.com/Jesse-Jumbo/program-design-II/main/src/main/resources/assets/image/readme/lose.png" alt="Lose" width="1000"/>
   <img src="https://raw.githubusercontent.com/Jesse-Jumbo/program-design-II/main/src/main/resources/assets/image/readme/win.png" alt="Win" width="1000"/>

6. 答對一題，敵方會扣愛心，反之答錯一題，我方會扣愛心，不論對錯與否，皆會給題目的詳解。
   <img src="https://raw.githubusercontent.com/Jesse-Jumbo/program-design-II/main/src/main/resources/assets/image/readme/correct.png" alt="Correct View" width="1000"/>
   <img src="https://raw.githubusercontent.com/Jesse-Jumbo/program-design-II/main/src/main/resources/assets/image/readme/incorrect.png" alt="Incorrect View" width="1000"/>

7. 通關 Level 1~4 之後，解鎖 Level 5。Level 5 需答對 10 題才可通關，失敗 5 題則遊戲失敗。
7. 通關後，解鎖該 Chapter 的劇情，根據劇情內容點擊滑鼠左鍵繼續遊戲。
   ![image](https://hackmd.io/_uploads/ryuZJF34C.png)

## 功能特點
- 簡單的滑鼠點擊操作
- 多樣的劇情內容和角色
- 多關卡設計，每關包含不同難度的問答挑戰
- 與 PD2 課程內容相關的問答，學習與娛樂結合
- 逐步解鎖的劇情，使遊戲充滿探索與挑戰性

## 技術細節
### 使用的主要技術、框架或庫
- **Java**: 遊戲的主要開發語言
- **Gradle**: 構建工具
- **Jackson**: 用於 JSON 處理
  - `com.fasterxml.jackson.core:jackson-databind:2.13.0`
  - `com.fasterxml.jackson.core:jackson-core:2.13.0`
  - `com.fasterxml.jackson.core:jackson-annotations:2.13.0`
  - `com.fasterxml.jackson.core:jackson-databind:2.12.3`
- **Gson**: JSON 處理庫
  - `com.google.code.gson:gson:2.8.8`
- **JLayer**: 用於播放 MP3 文件
  - `javazoom:jlayer:1.0.1`
- **JUnit**: 測試框架
  - `junit:junit:4.13.2`

## 貢獻指南
我們歡迎所有的貢獻者！如果你想要為這個專案做出貢獻，請遵循以下步驟：
1. Fork 這個倉庫。
2. 創建你的分支：
    ```sh
    git checkout -b feature/AmazingFeature
    ```
3. 提交你的修改：
    ```sh
    git commit -m 'Add some AmazingFeature'
    ```
4. 推送到分支：
    ```sh
    git push origin feature/AmazingFeature
    ```
5. 發給我們一個 Pull Request！

## 聯繫方式
如果你有任何問題或建議，可以直接透過 GitHub 聯繫我們三個開發者：
- 部政佑（Pukyle）[GitHub](https://github.com/pukyle)
- 江婕瀅（Jesse）[GitHub](https://github.com/Jesse-Jumbo/)
- 黃若慈（Rose）[GitHub](https://github.com/huang-rose)
