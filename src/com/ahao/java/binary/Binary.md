# 二进制相关知识

## 基础

- Java 没有无符号数
- 对于有符号数而言，0表示正数，1表示负数
- 正数的原码、反码、补码都一样
- 负数的反码 = 符号位不变，其它位0变为1，1变为0
- 负数的补码 = 负数的反码 + 1
- 0的反码补码都是0
- 计算机运算的时候都是以补码运算，所以无论什么运算，第一步就是要找到相应的数的补码
- 计算出来的结果也是以补码的形式存在：如果发现二进制计算结果是正数，那么结果就是这个数了，因为正数的三码合一，都是它自己；如果发现二进制计算结果是负数，那么这个数肯定是补码，还需要将其转换为原码

## 位运算

位运算是不认符号位的，就是说符号位会一起参与运算  
- &：按位与，两位全为1，则为1
- |：按位或，只要其中一位为1就为1
- ^：按位异或，两位不相同就为1，相同就位0
- ~：按位取反，每位0变1，1变0

### 位运算的例子

#### ~2

- ①找到2的补码（正数的三码合一）：00000000 00000000 00000000 00000010
- ②对2的补码进行按位取反操作：11111111 11111111 11111111 11111101
- ③发现取反过后的二进制结果是负数，那么这个值肯定就是最终结果的补码，所以要找到相应的原码
- ④先找到②的对应反码，②的结果 -1得到：11111111 11111111 11111111 11111100
- ⑤得到原码（符号位不变，其它位取反），即最终二进制结果：10000000 00000000 00000000 00000011
- ⑥所以最终结果为 -3

#### 2&3

- ①找到2的补码：00000000 00000000 00000000 00000010
- ②找到3的补码：00000000 00000000 00000000 00000011
- ③按位与的二进制结果为正数：00000000 00000000 00000000 00000010
- ④所以最终结果为 2

#### 2|3

- ①找到2的补码：00000000 00000000 00000000 00000010
- ②找到3的补码：00000000 00000000 00000000 00000011
- ③按位或的二进制结果为正数：00000000 00000000 00000000 00000011
- ④所以最终结果为 3

#### ~-5

- ①找到-5的补码  
    -5的原码为：10000000 00000000 00000000 00000101  
    -5的反码为（符号位不变，其他取反）：11111111 11111111 11111111 11111010  
    -5的补码为（反码 +1）：11111111 11111111 11111111 11111011  
- ②补码按位取反（发现结果是正数，那么这就是最终结果了）：00000000 00000000 00000000 00000100
- ③所以最终结果为 4

#### 13&7

- ①找到13的补码：00000000 00000000 00000000 00001101
- ②找到7的补码： 00000000 00000000 00000000 00000111
- ③按位与的二进制结果为正数：00000000 00000000 00000000 00000101
- ④所以最终结果为 5

#### 5|-4

- ①找到5的补码：00000000 00000000 00000000 00000101
- ②找到-4的补码  
    -4的原码为：10000000 00000000 00000000 00000100  
    -4的反码为：11111111 11111111 11111111 11111011  
    -4的补码为：11111111 11111111 11111111 11111100
- ③按位或的二进制结果为负数（那么注意了，这个不是最终结果，而是最终结果的补码）：11111111 11111111 11111111 11111101
- ④得到③对应的反码：11111111 11111111 11111111 11111100
- ⑤得到④对应的原码：10000000 00000000 00000000 00000011
- ⑥所以最终结果 -3

#### -3^3

- ①找到-3的补码  
    -3的原码为：10000000 00000000 00000000 00000011  
    -3的反码为：11111111 11111111 11111111 11111100  
    -3的补码为：11111111 11111111 11111111 11111101
- ②找到3的补码：00000000 00000000 00000000 00000011
- ③按位异或（不相同为1）的二进制结果为负数：11111111 11111111 11111111 11111110
- ④得到③的对应反码：11111111 11111111 11111111 11111101
- ⑤得到④的对应原码：10000000 00000000 00000000 00000010
- ⑥所以最终结果 -2

## 移位运算

- \>>：算术右移，低位溢出，符号位不变，用符号位补齐缺少的高位
- \<<：算术左移，符号位不变，缺少的低位用0补齐
- \>>>：逻辑右移，不管正负，反正溢出的就扔掉，缺少的就用0补齐

### 移位运算例子

#### 1>>2

- ①找到1的补码：00000000 00000000 00000000 00000001  
- ②右移两位，低位溢出（扔掉），符号位不变（依然是0），高位缺少的采用符号位（0）补全：000000 00000000 00000000 00000000
- ③所以最终结果是 0

#### -1>>2

- ①找到-1的补码
    -1的原码：10000000 00000000 00000000 00000001  
    -1的反码：11111111 11111111 11111111 11111110  
    -1的补码：11111111 11111111 11111111 11111111  
- ②右移两位，低位溢出（扔掉），符号位不变（依然是1），高位缺少的采用符号位（1）补全：11111111 11111111 11111111 11111111
- ③发现结果是负数，所以找到②的对应反码：11111111 11111111 11111111 11111110
- ④找到③对应的原码：10000000 00000000 00000000 00000001
- ⑤所以最终结果 -1

#### 1<<2

- ①找到1的补码：00000000 00000000 00000000 00000001  
- ②左移两位，符号位不变（依然是0），低位缺少的采用0补全：000000 00000000 00000000 00000100
- ④所以最终结果是 4

#### -1<<2

- ①找到-1的补码
    -1的原码：10000000 00000000 00000000 00000001  
    -1的反码：11111111 11111111 11111111 11111110  
    -1的补码：11111111 11111111 11111111 11111111  
- ②左移两位，符号位不变（依然是1），低位缺少的采用0补全：11111111 11111111 11111111 11111100
- ③发现结果是负数，所以找到②的对应反码：11111111 11111111 11111111 11111011
- ④找到③对应的原码：10000000 00000000 00000000 00000100
- ⑤所以最终结果 -4

#### 3>>>4

- ①找到3的补码：00000000 00000000 00000000 00000011
- ②右移四位，低位溢出（扔掉），高位缺少的全部0补齐：00000000 00000000 00000000 00000000
- ③所以最终结果 0

#### -3>>>29

- ①找到-3的补码：
    -3的原码：10000000 00000000 00000000 00000011  
    -3的反码：11111111 11111111 11111111 11111100  
    -3的补码：11111111 11111111 11111111 11111101
- ②右移29位，低位溢出（扔掉），高位缺少的全部0补齐：00000000 00000000 00000000 00000111
- ③所以最终结果 7