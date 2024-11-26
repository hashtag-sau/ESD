import axios from 'axios';
import { getAccessToken, getRefreshToken, saveTokens, removeTokens } from '../services/storage';

const axiosClient = axios.create({
  baseURL: 'http://localhost:8080/api/v1/employee'
});

axiosClient.interceptors.request.use((config) => {
  const token = getAccessToken();
  if (token) {
    console.log('tokeseeting is done');
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

axiosClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response?.status === 401) {
      console.log('got a 401 error, trying to refresh token');
      const originalRequest = error.config;
      const refreshToken = getRefreshToken();

      if (refreshToken && !originalRequest._retry) {
        originalRequest._retry = true;
        try {
          const { data } = await axios.post(
            'http://localhost:8080/api/v1/employee/refresh-token',
            null,
            {
              headers: {
                Authorization: `Bearer ${refreshToken}`
              }
            }
          );
          saveTokens(data.access_token, data.refresh_token);
          console.log('got new access token by refresh token');
          axios.defaults.headers.common.Authorization = `Bearer ${data.access_token}`;
          return axiosClient(originalRequest);
        } catch (err) {
          removeTokens();
          console.log('failed to get new access token by refresh token');
          // Redirect to login on failure
          window.location.href = '/';
        }
      }
    }
    return Promise.reject(error);
  }
);

export default axiosClient;
