import { useMutation } from "@tanstack/react-query";
import { updateNicknameApi, updatePasswordApi, updateProfileImgApi } from "../apis/userApi";

export const useUpdateProfileImgMutation = () => useMutation({
    mutationKey: ["useUpdateProfileImgMutation"],
    mutationFn: updateProfileImgApi,
    retry: 0,
});

export const useUpdateNicknameMutation = () => useMutation({
    mutationKey: ["useUpdateNicknameMutation"],
    mutationFn: updateNicknameApi,
    retry: 0,
});

export const useUpdatePasswordMutation = () => useMutation({
    mutationKey: ["useUpdatePasswordMutation"],
    mutationFn: updatePasswordApi,
    retry: 0,
});