/**@jsxImportSource @emotion/react */
import { useRecoilState } from 'recoil';
import * as s from './style';
import React from 'react';
import { mainSidebarIsOpenState } from '../../../atoms/mainSidebar/mainSidebarAtom';
import { FiChevronsRight } from 'react-icons/fi';
import { basicButton } from '../../../styles/buttons';

function MainContainer({ children }) { // 하위 컴포넌트 렌더링

    const [ isOpen, setOpen ] = useRecoilState(mainSidebarIsOpenState); 
    // 열려있는 상태인 Recoil의 atom 함수를 초기값으로 true, false

    const handleSidebarOpen = () => {
        setOpen(true)
    }

    return (
        <div css={s.container}>
            <header css={s.header}>
                {
                    !isOpen && 
                    <span css={s.sidebarOpenButton}>
                        <button css={basicButton} onClick={handleSidebarOpen}><FiChevronsRight /></button>
                    </span>
                }
            </header>
            <main css={s.main}>
                {children}
            </main>
        </div>
    );
}

export default MainContainer;