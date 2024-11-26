import axiosClient from '../utils/axiosClient';

export const login = async (email, password) => {
  const { data } = await axiosClient.post('/login', { email, password });
  return data;
};

export const refreshToken = async (refreshToken) => {
  const { data } = await axiosClient.post('/refresh', { refresh_token: refreshToken });
  return data;
};
