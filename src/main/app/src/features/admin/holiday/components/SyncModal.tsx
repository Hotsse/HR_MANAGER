import {Button, TextField} from "@mui/material";
import SaveButtonGroup from "../../../common/components/SaveButtonGroup.tsx";
import CustomModal from "../../../common/components/CustomModal.tsx";
import {useForm} from "react-hook-form";
import axios from "axios";
import {API_URL} from "../../../../constants/constants.ts";

const SyncModal = (props: Props) => {

    const {openModal, setOpenModal, onSearch} = props;
    const {
        register, getValues, reset
    } = useForm<HolidaySyncForm>();

    return (
        <CustomModal openModal={openModal}>
            <TextField
                {...register("yearMonth")}
                label={"연월"}
                variant={"standard"}
                fullWidth={true}
            />
            <SaveButtonGroup>
                <Button variant={"outlined"} onClick={close}>취소</Button>
                <Button variant={"contained"} onClick={save}>동기화</Button>
            </SaveButtonGroup>

        </CustomModal>
    );

    function close() {
        reset();
        setOpenModal(false);
    }

    function save() {
        const data = getValues();
        console.log(data);
        axios.post(`${API_URL}/api/admin/holiday/sync`, data)
            .then(() => {
                alert("동기화되었습니다.");
                reset();
                setOpenModal(false);
                onSearch(0);
            })
            .catch(() => {
                alert("동기화에 실패했습니다.");
            })
    }
}

type Props = {
    openModal: boolean,
    setOpenModal: (open: boolean) => void,
    onSearch: (page: number) => void,
}

type HolidaySyncForm = {
    yearMonth: string,
}

export default SyncModal;