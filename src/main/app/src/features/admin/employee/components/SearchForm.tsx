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
                    {...register("keyword")}
                    label={"사원ID"}
                    slotProps={{ inputLabel: { shrink: true } }}
                    variant={"standard"}
                    style={{ width: "300px" }}
                />
            </div>
            <ButtonGroup>
                <Button variant={"contained"} onClick={() => onSearch(0, getValues("keyword"))}>조회</Button>
            </ButtonGroup>
        </Box>
    );
}

type Props = {
    onSearch: (page: number, keyword?: string) => void;
}

export default SearchForm;