function TextInput({id, text}) { // 매개변수를 props로 받자마자 비구조 할당으로 키값을 가져옴
    const user = {               // 함수 매개변수에서 객체를 비구조화 할당을 하면 const 필요 없음 
        username: "test",
        password: "1234",
        name: "김준일",
    };

    const {username, name} = user; // 비구조 할당 : user에서 키 값을 빼온다 (변수할당 x)
    console.log(username);

    // label과 input 연결 - label 요소의 htmlFor 속성은 해당 input 요소의 id를 참조
    // label을 클릭하면 연결된 input으로 포커스가 이동
    return <div>
        <label htmlFor={id}>{text}</label> 
        <input type="text" id={id} />
    </div>
}
export default TextInput;