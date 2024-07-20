import React from "react";
import DrawerComponent from "./DrawerComponent";
import AppBarComponent from "./AppBarComponent";
import {useNavigate} from "react-router-dom";
import BottomComponent from "./BottomComponent";
import SimpleBottomNavigation from "./SimpleBottomNavigation";

export default function Controller( {title} ){
    const navigateTo = useNavigate();
    const [open, setOpen] = React.useState(false);
    const toggleDrawer = () => {setOpen(!open);}

    return (
        <>
            <AppBarComponent title={title} toggleDrawer={toggleDrawer} />

            <DrawerComponent
                open={open}
                toggleDrawer={toggleDrawer}
                navigate={navigateTo} />

            <BottomComponent />
        </>
    );
} 