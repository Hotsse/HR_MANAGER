import {AppBar, Box, IconButton, Menu, MenuItem, Toolbar, Typography} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import {useState} from "react";
import {useNavigate} from "react-router";

const HeaderBar = () => {

    const navigate = useNavigate();

    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);
    const handleClick = (event: React.MouseEvent<HTMLButtonElement>) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                        onClick={handleClick}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Menu
                        id="basic-menu"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                    >
                        <MenuItem onClick={() => navigate("/admin/department-management")}>부서 관리</MenuItem>
                        <MenuItem onClick={() => navigate("/admin/employee-management")}>사원 관리</MenuItem>
                    </Menu>
                    <Typography
                        onClick={() => window.location.href="/"}
                        variant="h6"
                        component="div"
                        sx={{ flexGrow: 1 }}>
                        인사시스템
                    </Typography>
                </Toolbar>
            </AppBar>
        </Box>
    );
}

export default HeaderBar;