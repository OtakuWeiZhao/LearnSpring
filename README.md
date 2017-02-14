# LearnSpring
##### Spring的Bean有哪些作用域(scope)
1. singleton:SpringIoc容器只会创建该Bean的唯一实例
2. prototype:每次请求都会创建一个实例
3. request:每次HTTP请求都会产生一个新的Bean该作用域仅在基于Web的
SpringApplicationContext情形下有效,session与global session也是如此
4. session:每次会话会创建一个实例
5. global session:全局的HttpSession中,容器会返回该bean的同一实例
