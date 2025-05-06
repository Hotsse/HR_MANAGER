import {createBrowserRouter} from "react-router-dom";
import DashboardPage from "../pages/DashboardPage.tsx";
import DepartmentManagementPage from "../pages/admin/department/DepartmentManagementPage.tsx";
import DefaultLayout from "../layouts/DefaultLayout.tsx";
import EmployeeManagementPage from "../pages/admin/employee/EmployeeManagementPage.tsx";
import PositionManagementPage from "../pages/admin/position/PositionManagementPage.tsx";
import HolidayManagementPage from "../pages/admin/holiday/HolidayManagementPage.tsx";

export const router = createBrowserRouter(
    [
        {
            path: "/",
            element: <DefaultLayout />,
            children: [
                {
                    index: true,
                    element: <DashboardPage />
                },
                {
                    path: "admin/department-management",
                    element: <DepartmentManagementPage />
                },
                {
                    path: "admin/position-management",
                    element: <PositionManagementPage />
                },
                {
                    path: "admin/employee-management",
                    element: <EmployeeManagementPage />
                },
                {
                    path: "admin/holiday-management",
                    element: <HolidayManagementPage />
                },
            ]
        },
    ]
);