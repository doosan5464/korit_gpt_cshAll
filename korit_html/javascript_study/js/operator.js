/*
연산자

- 산술연산자
    +, -, *, /, %, **(제곱)
*/
console.log(5 ** 2); // 25

/*
- 증감연산자
    ++, --
*/

/*
- 비교연산자
    <, <=, >, >=, ==, !=, ===
    javascript의 특징은 기본적으로 변수의 타입이 정해지지 않음
*/
console.log(1==1); // true
console.log(1=="1"); // true - 타입이 달라도 값이 같으면 같다고 나온다
console.log(1==="1"); // false - 타입과 값까지 검사한다
console.log(1!=="1"); // true
console.log(1!="1"); // false

/*
- 논리연산자
    &&, ||
    
    단축 평가
        논리 자료 값 || anything    -   or은 n + n : 그래서 true
            논리 자료 값 === true -> true
            논리 자료 값 === false -> anything
        
        논리 자료 값 && anything    -   and는 n * n : 그래서 false
            논리 자료 값 === true -> anything
            논리 자료 값 === false -> false
*/ 
console.log(1 === 1 || "김준일") // true
console.log(1 !== 1 || "김준일") // 김준일

console.log(1 === 1 && "김준일") // 김준일
console.log(1 !== 1 && "김준일") // false

/*
- Not 연산
    !, !!(not에 not. 그대로)

*/
console.log("-------Not-------");
console.log(!""); // true   -   비어있는 문자열은 false
console.log(!0); // true    -   값이 없거나 비어있다면 false 
console.log(!null); // true 
console.log(!undefined); // true 
console.log(![1,2,3,4,5]); // false
console.log(![]); // false  -   배열은 비어있어도 true (배열의 주소는 존재하기 때문에)
console.log(typeof []); // object (콘솔에서는 array)

var name = "";
if(!name) {
    console.log("이름이 비었습니다");
} // 이름이 비었습니다
name = "최석현";
if(!!name) {
    console.log("이름이 비어있지 않습니다");
} // 이름이 비어있지 않습니다. !! 쓰는 이유는 타입을 명확히 표시하려고 (boolean으로)

/*
- null 병합 연산
    anything1 ?? anything2
    좌항 === null || 좌항 === undefined
    -> anything2 

    좌항 !== null && 좌항 !== undefined
    -> anything1
*/
console.log("-----null 병합 연산-----")
console.log(null ?? "최석현2"); // 최석현2
console.log("최석현1" ?? "최석현2"); // 최석현1

console.log(![].length); // true    -   길이가 0. 비어있으니 false에 ! true