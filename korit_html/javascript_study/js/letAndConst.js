var a = 10;
console.log(a); // 10
var a = 20;
console.log(a); // 20 - 안되야 하는데 됨. 자바스크립트에선 됨. var 키워드 때문
console.log(b); // 오류가 나야하는데 undefined 가 뜸. 
var b = 30;

/*
호이스팅
    : 선언과 정의가 먼저 실행되는 현상 (a와 b의 선언을 먼저 실행)
        대입은 하지 않음. 그래서 위와 같이 a의 출력2번이 값이 다르고 b는 undefined가 나오게 된다
*/

let c = "c data"; // -> let : 일반 변수     -> 재선언이 불가능(var은 잘못 만든 타입)
console.log(c); // c data
// let c = "c2 data";  error 
c = "c2 data";
console.log(c); // c2 data

const d = "d data"; // -> const : 상수
console.log(d); // d data