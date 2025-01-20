function DataListLayout({children}) { // children은 props로 전달되는 특수한 키이며, 컴포넌트의 자식 요소를 나타냄

    console.log(children); // Array. 즉 children은 배열임
    const liList = [
        <li>5번리스트</li>,
        <li>6번리스트</li>,
        <li>7번리스트</li>,
        <li>8번리스트</li>,
    ]

    // children은 배열이니까 반복문 가능

    // children은 배열이니까 따로 배열 만들어서 return해도 같이 나온다
    return <ul> 
        {children}
        {liList}
        {
            children.map((li, index) => 
                <li key={index}>{"1" + li.props.children}</li> // {} 안에는 결국 하나의 자식요소
            )
        }
        {
            children.map((li, index) => 
                <li key={index}>{"2" + li.props.children}</li>
            )
        }
    </ul>
}
export default DataListLayout;