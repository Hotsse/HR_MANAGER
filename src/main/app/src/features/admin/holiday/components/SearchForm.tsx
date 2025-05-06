import {Box, Button, ButtonGroup, TextField} from "@mui/material";
import {useForm} from "react-hook-form";

const SearchForm = (props: Props) => {

    const {onSearch} = props;

    const {
        register, getValues
    } = useForm();

    return (
        <Box style={{ border: "1px solid", marginBottom: "40px", padding: "15px"}}>
            <div style={{ marginBottom: "10px"}}>
                <TextField
                    {...register("yearMonth")}
                    label={"연월"}
                    placeholder={"YYYYMM"}
                    slotProps={{ inputLabel: { shrink: true } }}
                    variant={"standard"}
                    style={{ width: "300px" }}
                />
            </div>
            <ButtonGroup>
                <Button variant={"contained"} onClick={() => onSearch(0, getValues("yearMonth"))}>조회</Button>
            </ButtonGroup>
        </Box>
    );
}

type Props = {
    onSearch: (page: number, yearMonth?: string) => void;
}

export default SearchForm;