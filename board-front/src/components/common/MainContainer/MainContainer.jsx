/**@jsxImportSource @emotion/react */
import { useRecoilState } from 'recoil';
import * as s from './style';
import React from 'react';
import { mainSidebarIsOpenState } from '../../../atoms/mainSidebar/mainSidebarAtom';
import { FiChevronsRight } from 'react-icons/fi';
import { basicButton } from '../../../styles/buttons';

function MainContainer({ children }) { // mainSidebarIsOpenState : 전역 Recoil 상태
    const [ isOpen, setOpen ] = useRecoilState(mainSidebarIsOpenState); 
    // isOpen : 현재 사이드바 상태 (true면 열림, false면 닫힘)

    const handleSidebarOpen = () => { // 사이드바 열기 버튼을 누르면 isOpen이 true
        setOpen(true)
    }

    return (
        <div css={s.container}>
            <header css={s.header}>
                {
                    !isOpen && // isOpen이 false일 때만 사이드바 열기 버튼을 표시
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