import AppBar from "@mui/material/AppBar";
import MenuIcon from "@mui/icons-material/Menu";
import * as React from "react";
import { Typography, Toolbar, IconButton } from "@mui/material";


export default function AppBarComponent({title, toggleDrawer}){

return (
<>
<AppBar elevation={10} position="absolute" color="secondary">
    <Toolbar>
        <IconButton size="large" onClick={toggleDrawer} color="primary">
            <MenuIcon/>
        </IconButton>
           
        <Typography sx={{pl : 2}} variant="h2" color="primary.light"> <b>{title}</b> </Typography>
    </Toolbar>
</AppBar>
</>);
}