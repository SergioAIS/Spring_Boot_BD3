import { useEffect, useState } from 'react'
import { listDepartments, deleteDepartment } from "../services/DepartmentService.js";
import { useNavigate } from "react-router-dom";

const ListAllDepartments = () => {

    const [datosDepartamentos, setDatosDepartamentos] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        getAllDepartments();
    }, []); // 👈 agregado para evitar bucle infinito

    function getAllDepartments() {
        listDepartments().then((response) => {
            console.log(response.data);
            setDatosDepartamentos(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }

    function addNewDepartment() {
        navigate("/AddDepartment");
    }

    function editDepartment(id) {
        navigate(`/edit-department/${id}`);
    }

    function removeDepartment(id) {
        deleteDepartment(id).then(() => {
            getAllDepartments();
        }).catch((error) => {
            console.log(error);
        });
    }

    return (
        <div className='container'>
            <h2>List of Departments</h2>
            <button className="btn btn-primary mb-2" onClick={addNewDepartment}>Add Department</button>
            <table className='table table-striped'>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Department Name</th>
                    <th>Department Description</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {
                    datosDepartamentos.map(department => (
                        <tr key={department.id}>
                            <td>{department.id}</td>
                            <td>{department.departmentName}</td>
                            <td>{department.departmentDescription}</td>
                            <td>
                                <button className="btn btn-info" onClick={() => editDepartment(department.id)}>Update</button>
                                <button className="btn btn-danger" onClick={() => removeDepartment(department.id)}>Delete</button>
                            </td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </div>
    )
}
export default ListAllDepartments
