import {createBrowserRouter} from "react-router-dom";
import DashboardPage from "../pages/DashboardPage.tsx";
import DepartmentManagementPage from "../pages/admin/department/DepartmentManagementPage.tsx";
import DefaultLayout from "../layouts/DefaultLayout.tsx";

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
                }
            ]
        },
    ]
);