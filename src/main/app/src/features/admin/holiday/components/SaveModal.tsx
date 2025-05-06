import {Button, TextField} from "@mui/material";
import SaveButtonGroup from "../../../common/components/SaveButtonGroup.tsx";
import CustomModal from "../../../common/components/CustomModal.tsx";
import {useForm} from "react-hook-form";
import axios from "axios";
import {API_URL} from "../../../../constants/constants.ts";
import {useEffect} from "react";
import {Holiday} from "../types/holiday.types.ts";

const SaveModal = (props: Props) => {

    const {openModal, setOpenModal, onSearch, initialData} = props;
    const {
        register, getValues, reset
    } = useForm<HolidaySaveForm>();

    useEffect(() => {
        reset({
            id: initialData?.id,
            holidayDate: initialData?.holidayDate,
            type: initialData?.type,
            description: initialData?.description,
        });
    }, [initialData]);

    return (
        <CustomModal openModal={openModal}>
            <TextField
                {...register("id")}
                label={"id"}
                variant={"standard"}
                fullWidth={true}
                disabled={true}
            />
            <TextField
                {...register("holidayDate")}
                label={"휴무일"}
                variant={"standard"}
                fullWidth={true}
            />
            <TextField
                {...register("type")}
                label={"휴무구분"}
                variant={"standard"}
                fullWidth={true}
            />
            <TextField
                {...register("description")}
                label={"비고"}
                variant={"standard"}
                fullWidth={true}
            />
            <SaveButtonGroup>
                <Button variant={"outlined"} onClick={close}>취소</Button>
                <Button variant={"contained"} onClick={save}>저장</Button>
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
        axios.post(`${API_URL}/api/admin/holiday`, data)
            .then(() => {
                alert("저장되었습니다.");
                reset();
                setOpenModal(false);
                onSearch(0);
            })
            .catch(() => {
                alert("저장에 실패했습니다.");
            })
    }
}

type Props = {
    openModal: boolean,
    setOpenModal: (open: boolean) => void,
    onSearch: (page: number) => void,
    initialData: Holiday | undefined,
}

type HolidaySaveForm = {
    id: number,
    holidayDate: string,
    type: string,
    description: string,
}

export default SaveModal;