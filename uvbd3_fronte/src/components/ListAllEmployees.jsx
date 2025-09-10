//import React from 'react'
import { useEffect, useState } from 'react'
import {listEmployees, updateEmployee, deleteEmployee} from "../services/EmployeeService.js";
import { useNavigate } from "react-router-dom";

const ListAllEmployees = () => {

    const [datosEmpleados, setDatosEmpleados] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        getAllEmployees();
    })

    function getAllEmployees() {
        listEmployees().then((response) => {
            console.log(response.data);
            setDatosEmpleados(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }

    function addNewEmployee() {
        navigate("/AddEmployee");
    }


    function updateEmployee(id) {
        navigate(`/edit-employee/${id}`);
    }

    function removeEmployee(id) {
        deleteEmployee(id).then(() => {
            getAllEmployees();
        }).catch((error) => {
            console.log(error);
        });
    }

    return (
        <div className='container'>
            <h2>List of Employees</h2>
            <button className="btn btn-primary mb-2" onClick={addNewEmployee}>Add Employee</button>
            <table className='table table-striped'>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                {
                    datosEmpleados.map(employee => (
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.address}</td>
                            <td>{employee.phone}</td>
                            <td>{employee.email}</td>
                            <td>
                                <button className="btn btn-info" onClick={()=> updateEmployee(employee.id)}>Update</button>
                                <button className="btn btn-danger" onClick={() => removeEmployee(employee.id)}>Delete</button>
                            </td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </div>
    )
}
export default ListAllEmployees
