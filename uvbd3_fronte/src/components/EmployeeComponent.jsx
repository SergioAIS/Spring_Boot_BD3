import { useState, useEffect  } from 'react'
import { useNavigate, useParams} from "react-router-dom";
import { addEmployee, getEmployee, updateEmployee } from '../services/EmployeeService.js'
import {listDepartments} from "../services/DepartmentService.js";

export const EmployeeComponent = () => {

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [address, setAddress] = useState("");
    const [department, setDepartment] = useState([]);
    const [departmentId, setDepartmentId] = useState("");

    useEffect(() => {
        listDepartments().then((response) => {
            setDepartment(response.data);
        }).catch((error) => {
            console.error(error);
        })
    }, [])

    const navigate = useNavigate();

    const { id } = useParams();

    useEffect(() => {
        if (id){
            getEmployee(id).then((response) => {
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
                setAddress(response.data.address);
                setPhone(response.data.phone);
                setDepartmentId(response.data.departmentId);
            }).catch(error => console.log(error));
        }
    }, [id])

    //Crear un objeto que inicialize los valores en vacio, para que nos sirva la validación de cada propiedad
    const [errors, setErrors] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        address: "",
        department: ''
    });

    function validateForm(){
        let valid = true;

        const errorsCopy = {...errors};

        if (firstName.trim()){
            errorsCopy.firstName = "";
        }else{
            errorsCopy.firstName = "First Name is required";
            valid = false;
        }
        if (lastName.trim()){
            errorsCopy.lastName = "";
        }else{
            errorsCopy.lastName = "Last Name is required";
            valid = false;
        }
        if (address.trim()){
            errorsCopy.address = "";
        }else{
            errorsCopy.address = "Address is required";
            valid = false;
        }
        if (email.trim()){
            errorsCopy.email = "";
        }else{
            errorsCopy.email = "Email is required";
            valid = false;
        }
        if (phone.trim()){
            errorsCopy.phone = "";
        }else{
            errorsCopy.phone = "Phone is required";
            valid = false;
        }

        //Vamos a usar Expresiones para validar que el email este bien escrito
        if (email.trim()){
            const validEmail = /^(?:[a-zA-Z0-9!#$%&'*+/=?^_{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_{|}~-]+)*|"[^"]+")@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,}$/;
            if (!validEmail.test(email)){
                errorsCopy.email = "Invalid email address";
                valid = false;
            }
        }

        //Vamos a usar Expresiones para validar que el número de teléfono tenga el formato correcto
        if (phone.trim()) {
            const validPhone = /^\+?[0-9]{7,15}$/;
            if (!validPhone.test(phone)) {
                errorsCopy.phone = "Invalid phone number";
                valid = false;
            }
        }

        if (departmentId){
            errorsCopy.department = ""
        }else{
            errorsCopy.department = "Select Department"
            valid = false;
        }


        setErrors(errorsCopy);
        return valid;
    }


    function saveEmployeeorUpdate(e){
        e.preventDefault()
        if (validateForm()){
            const employee = {firstName, lastName, email, phone, address, departmentId};
            console.log(employee);

            if(id){
                updateEmployee(id,employee).then((response) => {
                    console.log(response.data)
                   navigate('/emplolyees')
                }).catch(error => console.error(error));
            }else {
                addEmployee(employee).then((response) => {
                    console.log(response.data)
                    navigate('/employees')
                }).catch(error => console.error(error));
            }
        }
    }

    return (
        <div className="container">
            <br/>
            <div className="row">
                <div className="card col-md-6 offset-md-3">
                    <div className="card-body">
                        <form>
                            <div className="form-group">
                                <label className="form-label">First Name:</label>
                                <input type="text"
                                       placeholder="Enter Employee First Name"
                                       name="firstName"
                                       value={firstName}
                                       className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                                       onChange={(e) => setFirstName(e.target.value)}
                                />
                                {errors.firstName && <div className="invalid-feedback">{errors.firstName}</div>}
                            </div>
                            <br/>
                            <div className="form-group">
                                <label className="form-label">Last Name</label>
                                <input type="text"
                                       placeholder="Enter Employee Last Name"
                                       name="lastName"
                                       value={lastName}
                                       className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                                       onChange={(e) => setLastName(e.target.value)}
                                />
                                {errors.lastName && <div className="invalid-feedback">{errors.lastName}</div>}
                            </div>
                            <br/>
                            <div className="form-group">
                                <label className="form-label">Address</label>
                                <input type="text"
                                       placeholder="Enter Employee Address"
                                       name="address"
                                       value={address}
                                       className={`form-control ${errors.address ? 'is-invalid' : ''}`}
                                       onChange={(e) => setAddress(e.target.value)}
                                />
                                {errors.address && <div className="invalid-feedback">{errors.address}</div>}
                            </div>
                            <br/>
                            <div className="form-group">
                                <label className="form-label">Email</label>
                                <input type="text"
                                       placeholder="Enter Employee Email"
                                       name="email"
                                       value={email}
                                       className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                                       onChange={(e) => setEmail(e.target.value)}
                                />
                                {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                            </div><br/>

                            <div className="form-group">
                                <label className="form-label">Phone</label>
                                <input type="text"
                                       placeholder="Enter Employee Phone"
                                       name="phone"
                                       value={phone}
                                       className={`form-control ${errors.phone ? 'is-invalid' : ''}`}
                                       onChange={(e) => setPhone(e.target.value)}
                                />
                                {errors.phone && <div className="invalid-feedback">{errors.phone}</div>}
                            </div>
                            <br/>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Select Department:</label>
                                <select
                                    className={`form-control ${ errors.department ? 'is-invalid': '' }`}
                                    value={departmentId}
                                    onChange={(e) => setDepartmentId(e.target.value)}
                                >
                                    <option value="Select Department">Select Department</option>
                                    {
                                        department.map( department =>
                                            <option key={department.id} value={department.id} > {department.departmentName}</option>
                                        )
                                    }
                                </select>
                                { errors.department && <div className='invalid-feedback'> { errors.department} </div> }
                            </div>
                            <button className="btn btn-success" onClick={saveEmployeeorUpdate}>Submit</button>
                        </form>

                    </div>
                </div>
            </div>
            <br/>
        </div>
    )
}
export default EmployeeComponent