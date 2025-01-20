/*
함수
    - 매개변수의 수를 맞춰줄 필요는 없다
    - 자바 스크립트에선 오버로딩은 안되고 무조건 오버라이딩(재선언)밖에 안된다
    - 함수안에 함수 선언과 함수 호출도 가능하다 (파이썬도 가능)
    - 변수에 함수를 넣을 수 있다 (주소값을 넣는 것)
    - 배열에 함수를 넣을 수 있다
    - 매개변수로 함수를 넣을 수 있다
*/
fx1(); // 호이스팅
function fx1() {
    console.log("fx1 호출");
}
function fx2(num1, num2) {
    console.log(num1 + num2);
}
function fx2(num1, num2=0, num3=0) { // 매개변수가 없을 때 기본 값 지정
    console.log(num1 + (num2 ?? 0) + num3); // num2가 null이나 undefined가 아닐 경우에만 좌항(num2)를 반환. 아니라면 우항(0)을 반환해서 오류 안나게 
}
fx2(); // 오류안남. NaN (undefined + undefined는 숫자가 아니니까)
fx2(10); // 10 
fx2(10,20); // 30
fx2(10,20,30); // 60 
fx2(10); // 10

const num1 = 10;
function fx3() {
    return num1;
}
console.log(fx3()); // 10. 전역변수를 함수에서 쓸 수가 있다

function fx4() {
    console.log("fx4 호출");

    function fx5() {
        console.log("fx5 호출");
    }
    fx5();
}
fx4();

const fx6 = fx4; // 변수에 함수를 넣음
fx6();

function fx11() {
    console.log("fx11 호출");
}
function fx12() {
    console.log("fx12 호출");
}
function fx13() {
    console.log("fx13 호출");
}
const fxArray = [fx11,fx12,fx13];
console.log(fxArray); // [ [Function: fx11], [Function: fx12], [Function: fx13] ]

for(let fx of fxArray) { // 그래서 forEach문으로 함수의 호출도 가능함
    fx();
}

// callback 함수
function add(n1, n2) {
    return n1 + n2;
}
function sub(n1, n2) {
    return n1 - n2;
}
function run(fx) {
    const result = fx(10, 20);
    console.log(result);
}
run(add); // 30. 이렇게 하면 매개변수에 함수를 넣는 방식에 따라 유연하게 가능
run(sub); // -10 

/*
화살표 함수(람다와 동일)
    - 보통 바깥 함수는 function
    - 안쪽 함수는 => 화살표 함수로
*/
// 기존 함수
function fx(arg1, arg2) {
    return "리턴";
}
fx(1, 2);

// 화살표 함수s
const arrowfx = (arg1, arg2) => {
    return "리턴";
}
arrowfx(3, 4);

const arrowfx2 = arg1 => arg1 + 1;
arrowfx2(10)

function run(fx) {
    fx();
}
run(() => console.log("test"));








