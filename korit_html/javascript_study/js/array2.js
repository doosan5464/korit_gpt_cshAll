const user1 = {
    username: "aaa",
    password: "1234",
}
const user2 = {
    username: "bbb",
    password: "1234",
}
const user3 = {
    username: "ccc",
    password: "1234",
}

const users = [user1, user2, user3];
const usersInfoString = users.map(user => `사용자명: ${user.username} 비밀번호: ${user.password}`); // 알아서 반복함  
// map은 Array 메서드로 사용. 배열의 각 요소를 순회하며, 제공된 콜백 함수를 적용한 결과를 새로운 배열로 반환

// html에서 자주 사용하는 반복문?
const userConponents = users.map(user => 
    `
        <label>사용자이름</label>
        <div>${user.username}</div>
        <label>비밀번호</label>
        <div>${user.password}</div>
    `
).join(""); // 배열 사이에 있는 ,를 없앨려고 공백으로 줌
console.log(userConponents);

const users2 = users.filter(user => user.username !== "bbb"); // filter() : 참인것만 담아라
console.log(user2); // { username: 'bbb', password: '1234' }

// 문제 ccc 제거 bbb는 ddd가 되야한다
const users3 = users.filter(user => user.username !== "ccc");
users3[1].username = "ddd";
console.log(users3)
// 이렇게 풀라는건지는 모르겠음

// 강사님 풀이
const users4 = users.map(user => { // 새로운 배열을 담을 변수에 if를 쓰면서 바꿈
    if(user.username === "bbb") {
        return {
            ...user, // 이렇게하면 일일이 수많은 필드들을 쓸 필요가 없어진다
            username: "ddd", // 없으면 추가, 있다면 수정
        };
    }
    return user;
}).filter(user => user.username !== "ccc"); // 아직 배열이기 때문에 .filter 가능
console.log(users4)
/*[
  { username: 'aaa', password: '1234' },
  { username: 'ddd', password: '1234' }
]*/

// 이해를 위한 map 예시
const nums = [1,2,3,4,5];
const nums2 = nums.map(n => {
    if(n % 2 === 0) {
        return n * 2;
    }
        return n;
});
console.log(`${nums} ===> ${nums2}`)
/*
[1,2,3,4,5] => [1,4,3,8,5]
*/