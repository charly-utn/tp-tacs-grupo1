import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/'

export const instance = axios.create({
    baseURL: BASE_URL
})

  //   this.axiosInstance = axios.create({
  //     baseURL: this.apiBaseUrl,
  //     withCredentials: true,
  //     headers: {
  //       'Content-Type': 'application/json'
  //     },
  //     methods: ['get', 'post', 'put', 'delete']
  //   });
  // }

  instance.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
  instance.interceptors.request.use(config => {
    config.headers.Authorization = localStorage.getItem('token');
    return config;
  })