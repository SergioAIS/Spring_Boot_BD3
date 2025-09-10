import axios from "axios";

const REST_API_BASE_URL = "http://localhost:9090/api/departments";

export const listDepartments = () => axios.get(REST_API_BASE_URL);

export const addDepartment = (department) => axios.post(REST_API_BASE_URL, department);

export const getDepartment = (departmentID) => axios.get(`${REST_API_BASE_URL}/${departmentID}`);

export const updateDepartment = (departmentID, department) => axios.put(`${REST_API_BASE_URL}/${departmentID}`, department);

export const deleteDepartment = (departmentID) => axios.delete(`${REST_API_BASE_URL}/${departmentID}`);
