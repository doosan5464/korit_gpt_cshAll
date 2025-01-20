function DataListLayout2({dataList}) { // [1,2,3,4,5]
    return <ul>
        {
            dataList.map((temp, index) =>
                <li key={index}>{temp}</li>
            )
        }
    </ul>
}
export default DataListLayout2;