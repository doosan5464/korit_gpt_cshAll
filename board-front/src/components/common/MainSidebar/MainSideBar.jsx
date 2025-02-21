/**@jsxImportSource @emotion/react */
import * as s from './style';
import React from 'react';
import { FiChevronsLeft } from "react-icons/fi";
import { basicButton, emptyButton } from '../../../styles/buttons';
import { useRecoilState } from 'recoil';
import { mainSidebarIsOpenState } from '../../../atoms/mainSidebar/mainSidebarAtom';
import { LuLockKeyhole } from "react-icons/lu"; // react-icon 에서 가져옴 (FiChevronsLeft)

function MainSidebar(props) {
    const [ isOpen, setOpen ] = useRecoilState(mainSidebarIsOpenState);
    // mainSidebarIsOpenState를 다른 컴포넌트 (MainContainer)와 공유하면서 동작함

    const handleSidebarClose = () => { // 사이드바 닫기 버튼을 누르면 isOpen이 false
        setOpen(false);
    }

    return (
        <div css={s.layout(isOpen)}>
            <div css={s.container}>
                <div css={s.groupLayout}>
                    <div css={s.topGroup}>
                        <div css={s.user}>
                            <button css={emptyButton}>
                                <span css={s.authText}>
                                    <LuLockKeyhole />로그인 후 이용하기
                                </span>
                            </button>
                        </div>
                        <button css={basicButton} onClick={handleSidebarClose}><FiChevronsLeft /></button>
                    </div>
                </div>
                
            </div>
        </div>
    );
}

export default MainSidebar;