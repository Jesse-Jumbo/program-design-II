# Program Design II
![螢幕擷取畫面 2024-06-04 193850](https://hackmd.io/_uploads/ByRC2_hEA.png)
## 遊戲說明

《PROGRAM DESIGN II》是由部政佑、江婕瀅、黃若慈自主研發的一款操作極簡的問答類戰鬥RPG。遊戲發生在一個被稱作「國立成功大學」的幻想世界。在這裡，被選中的人們將會被授予「Java大法」，獲得物件導向之力。你將扮演一位名為「大學生」的神秘角色，在自由的旅行中邂逅性格各異、能力獨特的同伴們，和他們一起擊敗強敵、找回失散的學分——與此同時，逐步發掘「程式設計二」的真相。

## Requirements

- Java == 1.8.0_401（Oracle JDK）/1.8.0_402（OpenJDK）

## 遊戲簡介

跟敵人戰鬥時，透過回答成功Java教材題庫的問題來對敵人造成傷害。

## 遊戲機制

《PROGRAM DESIGN II》為回合制戰鬥RPG，遊戲戰鬥流程圖如下：

![image](https://hackmd.io/_uploads/Bk49Cdn4C.png)

### 過關條件

- 敵人的心形生命值全數歸零。

### 失敗條件

- 我方的心形生命值歸零。

## 遊戲操作

- 滑鼠左鍵：選擇選項。

## 遊戲玩法

遊戲內容主要分為兩個部分：劇情對話跟問答戰鬥。

### 劇情對話

《PROGRAM DESIGN II》的劇情採線性推進，一共分為五個章節。玩家點擊滑鼠左鍵，以進到下一段對白。

對話畫面（Dialogue screen diagram):

![image](https://hackmd.io/_uploads/ryuZJF34C.png)

劇情畫面（Plot screen diagram):

![image](https://hackmd.io/_uploads/S1e_1t34A.png)

如下圖所示，當玩家點擊滑鼠左鍵，就會進到下一段對白：

![image](https://hackmd.io/_uploads/SJr3kF2V0.png)

此外，部分對話設有選項，用滑鼠左鍵選擇選項，將會有不同劇情或對話：

![image](https://hackmd.io/_uploads/ByB0yY24C.png)

### 問答戰鬥

- **戰鬥開始介面**：

  - 遊戲開始時顯示 "BATTLE BEGIN"，背景是一堵磚牆，營造出戰鬥準備的氣氛。
  
![image](https://hackmd.io/_uploads/S1e4eYn40.png)
  
- **角色位置**：

  - 玩家控制的角色跟血量位於遊戲畫面的左側。
  - 敵人角色跟血量出現在遊戲畫面的右側。
   
![image](https://hackmd.io/_uploads/S1XIlt3VC.png)
  
- **問題與作答界面**：

  - 每個戰鬥輪次開始時，會出現一個問題和四個選項（A, B, C, D），玩家需要在30秒內選擇答案。

![image](https://hackmd.io/_uploads/rk6AgK3NR.png)

- **作答計時與回應**：

  - 每題有30秒的作答時間。如果玩家在時間內作答正確，則可以繼續挑戰下一題。
  - 如果玩家答錯或超過時間未作答，則會受到來自敵人的攻擊，並可能失去一顆心形生命值。

![image](https://hackmd.io/_uploads/HJfQ-F2NC.png)

- **生命值和戰鬥結果**：

  - 玩家的生命值和敵人的生命值都顯示在屏幕的上方，以心形符號表示。
  - 每次玩家回答正確，敵人將受到攻擊並可能失去一顆心。
  - 若敵人的心形生命值耗盡，玩家則獲得勝利。

![image](https://hackmd.io/_uploads/r1XFZFhV0.png)

- **勝利介面**：

  - 當敵人的心形生命值歸零後，遊戲將顯示 "VICTORY" 界面，慶祝玩家的勝利。

![image](https://hackmd.io/_uploads/rJ-jWY24A.png)

- **問題的反饋與學習**：

  - 無論攻擊成功與否，每題結束後都會顯示正確答案和相關解釋，增加學習和互動的元素。

![image](https://hackmd.io/_uploads/ryeC-Y24C.png)

此戰鬥設計不僅提供了刺激的遊戲體驗，也允許玩家通過問題來學習知識，寓教於樂。
