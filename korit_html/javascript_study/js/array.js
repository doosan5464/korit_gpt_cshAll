/*
배열
*/

const numbers = [1,2,3,4,5,6,7,8,9]; // 배열의 주소만 상수로써 고정. 값은 바꿀 수 있음
console.log(numbers); // 배열 출력

for(let i = 0; i < numbers.length; i++) {
    console.log(numbers[i]);
} // 배열의 인덱스번호의 원소를 출력

for(let num of numbers) { // foreach에서 : 대신에 of
    console.log(num);
}

numbers.forEach((num) => console.log(num)); // 람다식 가능 : -> 대신 =>

numbers.push(10);   // 자바스크립트 배열은 stack구조. push
console.log(numbers);
console.log(numbers.pop()); // 10. 자바에서는 실행창에서 삭제하기 때문에 주소에 있는 9는 삭제안함. 출력때만
                            // 자바 스크립트에서는 진짜로 함
console.log(numbers.includes(5)); // true. 값을 포함하고 있는지 확인. includes (자바에선 contains)
console.log(numbers.indexOf(5)); // 4. 값의 위치 확인. indexOf
console.log(numbers.lastIndexOf(5)); // 4. 뒤에서부터의 값의 위치 확인. lastIndexOf
console.log(numbers.concat([11, 12, 13, 14, 15])); // 두 배열의 병합(그냥 이어 붙임). concat    ->      numbers에 적용시키는게 아니라 새로운 배열을 만드는 것

console.log("ㄹ호ㅜㅡㄹ하ㅣㅗㄹ후ㅡㅚㅏ"); // 그대로임. 출력문
console.log(numbers); // 그대로임. 출력문

const newNumbers = numbers.concat([11, 12, 13, 14, 15]);
console.log(newNumbers);

const newNumbers2 = [...numbers, 11, 12, 13, 14, 15]; // ... : 스프레드 연산(배열의 안에 있는 값들만 복사하여 가져옴) 이렇게도 가능. 나중에 배움
console.log(newNumbers2);

const newNumbers3 = numbers.slice(0, 5) // 파이썬의 슬라이싱과 동일. 새로운 배열 생성. slice
                                        // 첫 인덱스는 고정. 끝 인덱스(-1)는 생략 가능(생략시 끝까지). 
console.log(newNumbers3); // [ 1, 2, 3, 4, 5 ]

// 숫자 4 없애기 (사실상 새로운 배열에 새로 담기)
const newNumbers4 = numbers.slice(0, numbers.indexOf(4))
                           .concat(numbers.slice(numbers.indexOf(4) + 1));
console.log(newNumbers4);                           

// 이걸 더 간편하게
const newNumbers5 = numbers.splice(3, 1); // 원하는 인덱스의 값을 실제로 지우고 반환. splice(인덱스번호, 몇개) -> 뒤에 몇개 생략시 인덱스번호부터 다 지움
                                          // 매개변수 2개외에 더 넣으면 지운 자리에 그 값을 대신 넣어줌
console.log(newNumbers5); // [4]. 반환된 4
console.log(numbers); // 4가 없음 실제로 제거됨

const newNumbers6 = numbers.splice(4, 2, 11, 12); // 4번 인덱스 부터 2개 지움. (6, 7)
console.log(newNumbers6); // [6, 7]
console.log(numbers); // [1, 2, 3, 5, 11, 12, 8, 9]

numbers.splice(4, 0, 20, 21); // 0을 주면 아무것도 제거하지 않음. 즉 20과 21만 추가하게 됨 4번 인덱스부터
console.log(numbers); // s[1, 2, 3, 5, 20, 21, 11, 12, 8, 9]