/*
객체 
*/

const obj = { // 객체 선언 ()가 없음
    name: "김준일",
    age: 31,
    print: () => console.log(`이름: ${obj.name}, 나이: ${obj.age}`), // tab위에 ` ` : 안에 ${} 이런 대입? 할 때 사용 
                                                                    // 같은 곳에 있지만 객체의 변수는 obj.~ 이런식으로 써야 한다
}                                                                   // this 안됨 
obj.print(); // 이름: 김준일, 나이: 31
const obj2 = {
    name: "김준일",
    age: 31,
    print: () => console.log(`이름: ${obj.name}, 나이: ${obj.age}`), 
}
console.log(obj === obj2); // false
console.log("34324234s")
console.log(JSON.stringify(obj) === JSON.stringify(obj2)); // true. JSON으로 변환후 비교
console.log(typeof JSON.stringify(obj)) ; // string

/*
object -> Json  JSON.stringfy(object)
Json -> object  JSON.parse(json문자열)

User 객체 생성
username, password, name, email
*/

const user = { // 객체. () 없음
    username: "",
    password: "",
    name: "",
    email: "",
    hobby: ["축구", "농구", "배구"],

    address: {
        si: "부산시",
        gungu: "부산진구",
    },

    printUserInfo: () => console.log("사용자 정보 출력"),
}
user.username = "junil";
user.password = 1234;
user.name = "김준일";
user.email = "junil@gmail.com";

console.log(user); // 함수가 있음
const userJson = JSON.stringify(user);
console.log(userJson);  // 함수 없어짐
const convertUser = JSON.parse(userJson); 
console.log(convertUser); // 원래 있던 함수가 사라짐
// js -> JSON
// : key:value 만 가져가고 다른건 안된다


// 객체의 필드 속성을 바꾸는 법
const student = { // 자바스크립트의 객체는 자바의 Map과 같다
    name: "김준일",
    age: 31,
    addrress : "부산 동래구",
    name: "김준이", // Map에서는 키값 중복이 안됨. 그래서 그냥 값을 덮어버림
}
console.log(student) // { name: '김준이', age: 31, addrress: '부산 동래구' }  "김준이"가 덮어 씌워졌다

const key = "age"; // 필드의 변수화
const value = 32;

const student2 = {
    ...student,  // 객체 안에 있는걸 복사하여 가져옴. 위에 필드 3개와 동일
    [key]: value, // [변수]: 값 - 이렇게 써야 필드가 아니라 변수로써 사용 가능하다
}
console.log(student2) // { name: '김준이', age: 32, addrress: '부산 동래구' }   age 32가 됨