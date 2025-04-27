import {ButtonGroup} from "@mui/material";

const SaveButtonGroup = (props: Props) => {

    const {children} = props;

    return (
        <ButtonGroup className={"save-button-group"} size={"large"} fullWidth={true}>
            {children}
        </ButtonGroup>
    )
}

type Props = {
    children: React.ReactNode
}

export default SaveButtonGroup;