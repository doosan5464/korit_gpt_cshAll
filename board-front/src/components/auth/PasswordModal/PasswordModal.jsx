/**@jsxImportSource @emotion/react */
import * as s from './style';
import React, { useState } from 'react';
import { RiCloseCircleFill } from "react-icons/ri";
import { CgPassword } from "react-icons/cg";
import { useUpdatePasswordMutation } from '../../../mutations/accountMutation';
import Swal from 'sweetalert2';

// 비밀번호 변경 모달 컴포넌트
function PasswordModal({ setOpen }) {
    // 비밀번호 변경을 위한 mutation hook
    const passwordMutation = useUpdatePasswordMutation();

    // 비밀번호 입력 상태 관리
    const [passwordValue, setPasswordValue] = useState({
        newPassword: "",
        confirmPassword: "",
    });

    // 입력값 변경 시 상태 업데이트
    const handlePasswordInputOnChange = (e) => {
        setPasswordValue(prev => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
    }

    // 비밀번호 설정 버튼 클릭 시 비밀번호 변경 처리
    const handleSetButtonOnClick = async () => {
        // 비밀번호 변경 mutation 수행
        await passwordMutation.mutateAsync(passwordValue.newPassword);
        
        // 성공 메시지 팝업
        await Swal.fire({
            titleText: "새로운 비밀번호로 변경되었습니다.",
            icon: "success",
            showConfirmButton: false,
            timer: 1000,
            position: "center",
        });

        // 모달 닫기
        setOpen(false);
    }

    // 모달 닫기 버튼 클릭 시 모달 닫기
    const handleCloseButtonOnClick = () => {
        setOpen(false);
    }

    return (
        <div>
            <div css={s.modalTop}>
                <div onClick={handleCloseButtonOnClick}><RiCloseCircleFill /></div>
            </div>
            <div css={s.header}>
                <div css={s.headerIcon}><CgPassword /></div>
                <h2 css={s.headerTitle}>Set a password</h2>
                <p css={s.headerMessage}>비밀번호는 최소 8자 이상, 또는 16자 이하의 영문, 숫자 조합을 사용하세요.</p>
            </div>
            <div>
                <div css={s.inputGroup}>
                    <label>Enter a new password</label>
                    <input type="password" name='newPassword' 
                        value={passwordValue.newPassword} 
                        onChange={handlePasswordInputOnChange} />
                </div>
                <div css={s.inputGroup}>
                    <label>Confirm your new password</label>
                    <input type="password" name='confirmPassword' 
                        value={passwordValue.confirmPassword} 
                        onChange={handlePasswordInputOnChange} />
                </div>
                <button 
                    css={s.setButton} 
                    disabled={!passwordValue.newPassword || !passwordValue.confirmPassword}
                    onClick={handleSetButtonOnClick}
                >Set a password</button>
            </div>
        </div>
    );
}

export default PasswordModal;