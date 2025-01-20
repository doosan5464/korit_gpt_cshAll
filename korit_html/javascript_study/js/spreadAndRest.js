const obj = {
    data1: "d1",
    data2: 10,
    data3: [1,2,3,4],
};
// 깊은복사 : 그대로 가져와도 {} 로 새로운 객체를 만들면 주소가 다름.
const obj2 = {  
    ...obj,
    data2: 20,

};

/*
비구조 할당(구조분해)

const data1 = obj.data1;
const data2 = obj.data2;
    -> 이거 2개가 아래와 같음

배열이면 [], 객체면 {}
*/
const {data1:d1, data2:d2} = obj; // 키값이 있어서 키값을 맞춰줘야 함, :을 써주면 변수명을 바꿀 수 있음
console.log(d1, d2); // d1 10

const nums = [1,2,3,4,5];
const [n1,n2,n3] = nums; // 객체는 키값이 있어서 저래야 하지만 배열은 그냥 변수 새롭게 지정(순서대로 할당)   
console.log(n1, n2, n3); // 1 2 3

// 갖고 오기 싫은 필드가 있다면
const {data1: dd1, ...obj3} = obj; // 여기서의 ...은 Rest(나머지). 변수명을 새로 지어주면 된다. 
console.log(obj3) // { data2: 10, data3: [ 1, 2, 3, 4 ] }   ->   필요 없는 필드는 앞에 미리 빼주고(data1:dd1) ...obj3 으로 나머지를 다 가져왔으니 그걸 호출

const [n4, n5, ...newNums] = nums;
console.log(newNums) // 3 4 5

