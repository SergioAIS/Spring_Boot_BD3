import { useState, useEffect } from 'react'
import { useNavigate, useParams } from "react-router-dom";
import { addDepartment, getDepartment, updateDepartment } from '../services/DepartmentService.js'

export const DepartmentComponent = () => {

    const [departmentName, setDepartmentName] = useState("");
    const [departmentDescription, setDepartmentDescription] = useState("");

    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        if (id) {
            getDepartment(id).then((response) => {
                setDepartmentName(response.data.departmentName);
                setDepartmentDescription(response.data.departmentDescription);
            }).catch(error => console.log(error));
        }
    }, [id]);

    // Validaciones
    const [errors, setErrors] = useState({
        departmentName: "",
        departmentDescription: "",
    });

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors };

        if (departmentName.trim()) {
            errorsCopy.departmentName = "";
        } else {
            errorsCopy.departmentName = "Department Name is required";
            valid = false;
        }

        if (departmentDescription.trim()) {
            errorsCopy.departmentDescription = "";
        } else {
            errorsCopy.departmentDescription = "Department Description is required";
            valid = false;
        }

        setErrors(errorsCopy);
        return valid;
    }

    function saveDepartmentOrUpdate(e) {
        e.preventDefault();
        if (validateForm()) {
            const department = { departmentName, departmentDescription };
            if (id) {
                updateDepartment(id, department).then((response) => {
                    console.log(response.data);
                    navigate('/departments');
                }).catch(error => console.log(error));
            } else {
                addDepartment(department).then((response) => {
                    console.log(response.data);
                    navigate('/departments');
                });
            }
        }
    }

    return (
        <div className="container">
            <br />
            <div className="row">
                <div className="card col-md-6 offset-md-3">
                    <div className="card-body">
                        <form>
                            <div className="form-group">
                                <label className="form-label">Department Name:</label>
                                <input
                                    type="text"
                                    placeholder="Enter Department Name"
                                    name="departmentName"
                                    value={departmentName}
                                    className={`form-control ${errors.departmentName ? 'is-invalid' : ''}`}
                                    onChange={(e) => setDepartmentName(e.target.value)}
                                />
                                {errors.departmentName && <div className="invalid-feedback">{errors.departmentName}</div>}
                            </div>
                            <br />
                            <div className="form-group">
                                <label className="form-label">Department Description:</label>
                                <input
                                    type="text"
                                    placeholder="Enter Department Description"
                                    name="departmentDescription"
                                    value={departmentDescription}
                                    className={`form-control ${errors.departmentDescription ? 'is-invalid' : ''}`}
                                    onChange={(e) => setDepartmentDescription(e.target.value)}
                                />
                                {errors.departmentDescription && <div className="invalid-feedback">{errors.departmentDescription}</div>}
                            </div>
                            <br />
                            <button className="btn btn-success" type="submit" onClick={saveDepartmentOrUpdate}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
            <br />
        </div>
    )
}

export default DepartmentComponent
