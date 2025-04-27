import HeaderBar from "../features/common/components/HeaderBar.tsx";
import {Container} from "@mui/material";
import { Outlet } from "react-router-dom";

const DefaultLayout = () => {

    return (
        <>
            <HeaderBar />
            <Container className="main-container">
                <Outlet />
            </Container>
        </>
    );
}

export default DefaultLayout;