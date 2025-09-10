
import './App.css'
import ListAllEmployees from "./components/ListAllEmployees.jsx";
import ListAllDepartments from "./components/ListAllDepartments.jsx";

import {BrowserRouter, Routes, Route} from 'react-router-dom'
import HeaderComponent from "./components/HeaderComponent.jsx";
import FooterComponent from "./components/FooterComponent.jsx";
import {EmployeeComponent} from "./components/EmployeeComponent.jsx";
import DepartmentComponent from "./components/DepartmentComponent.jsx";

function App() {


    return (
        <>
            <BrowserRouter>
                <HeaderComponent />
                <Routes>
                    <Route path="/" element={<ListAllEmployees />} />
                </Routes>
                <Routes>
                    <Route path="/employees" element={<ListAllEmployees />} />
                </Routes>
                <Routes>
                    <Route path="/AddEmployee" element={<EmployeeComponent />} />
                </Routes>
                <Routes>
                    <Route path="/edit-employee/:id" element={<EmployeeComponent />} />
                </Routes>


                <Routes>
                    <Route path="/" element={<ListAllDepartments />} />
                </Routes>
                <Routes>
                    <Route path="/departments" element={<ListAllDepartments />} />
                </Routes>
                <Routes>
                    <Route path="/AddDepartment" element={<DepartmentComponent />} />
                </Routes>
                <Routes>
                    <Route path="/edit-department/:id" element={<DepartmentComponent />} />
                </Routes>
                <FooterComponent />
            </BrowserRouter>
        </>
    )
}

export default App
