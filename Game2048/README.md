# 2048

## 游戏信息

游戏名称：2048（参照[牛客网代码](https://git.nowcoder.com/11000160/2048-java.git)）

游戏规则：（大家应该很熟悉了，直接摘抄百度的）控制所有方块向同一个方向运动，两个相同数字方块撞在一起之后合并成为他们的和，每次操作之后会随机生成一个2或者4，最终得到一个“2048”的方块就算胜利了。

游戏操作：
- 鼠标点击开始或者重新开始游戏
- 键盘方向键上、下、左、右

---

### 项目模块

编写难度指数：★★☆☆☆

基础模块：
1. JavaFX视图布局fxml文件
2. 常量类（主要包含视图配色及全局游戏状态的枚举）
3. 方格实体
4. 控制类（核心逻辑实现）
5. 启动类

---

关键点：
- 视图绘制
> 精准计算方格大小及间隙
>>
> 做好色块划分
- 方块抽象
> 数值
>>
> 标志
- 移动合并
> 合并算法
- 游戏状态
> 全局
>>
> 开始
>>
> 进行中
>>
> 获胜
>>
> 失败

---

实现原理：
1. 牛客的源代码是基于Java Swing的，这里我们采用JavaFX实现；主要改动的地方在于视图组件，其核心控制代码其实是一样的
2. 在JavaFX中使用主面板+画板搭建主界面框架，在画板里面绘制游戏界面（主界面背景色+4x4方格+游戏提示文字）
3. 基于JavaFX对鼠标和键盘的监听，实现游戏开始、方块移动和合并操作
4. 每个方块就是一个对象，包含数值和合并标志
5. 每次方块移动时，进行合并及视图重绘；移动结束时，若尝试无法移动，则游戏失败
6. 当有方块达到“2048”数值时，游戏获得胜利

