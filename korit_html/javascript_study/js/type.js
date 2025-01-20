// 자바 스크립트는 초기화 되어질 때 타입이 정해진다
// 자바 스크립트의 변수는 타입이 정해지지 않고 들어가는 값에 따라 바뀐다
var name = "최석현";
console.log(name);
console.log(typeof name); // string
var age = 25;
console.log(age);
console.log(typeof age); // number
var address;
console.log(address); // undefined - 정의되지 않은b 
console.log(typeof address); // undefined도 하나의 타입임
address = 10;
console.log(address); // 10
console.log(typeof address); // number
address += "10";
console.log(address); // 1010
console.log(typeof address); // string
                             // 초기화 되어질 때 타입이 정해지기 때문에 int에서 string으로 바뀐 것

address = null;
console.log(address); // null
console.log(typeof address); // object - null은 객체를 표현할 때 사용
address = {
    si: "부산시",
    gungu: "부산진구"
};
console.log(address); // { si: '부산시', gungu: '부산진구' }
console.log(typeof address); // object


/*
number (숫자 - 정수, 실수)
string (문자열 - 문자 이런거없이 다 통합)
boolean (참, 거짓)
object (객체 -  null 포함)
undefined (type x)
NaN (Not a Number) 
*/