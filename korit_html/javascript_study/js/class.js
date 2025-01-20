class User { // 자바 스크립트에서도 클래스를 쓸 수 있지만 잘 안씀
    username;
    password;
    name;
    email;

    constructor(username = null, password = null, name = null, email = null) { // 생성자
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    toString() { // function 안써도 됨
        return `User = { username: ${this.username}, password: ${this.password} }`;
    }
}
const user = new User("aaa", 1234);
console.log(user.toString());