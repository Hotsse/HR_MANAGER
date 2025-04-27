import {Box, Modal} from "@mui/material";

const CustomModal = (props: Props) => {

    const {openModal, children} = props;

    const style = {
        position: 'absolute',
        top: '40%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 800,
        bgcolor: 'background.paper',
        boxShadow: 24,
        p: 4,
    };

    return (
        <Modal
            open={openModal}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <Box sx={style}>
                {children}
            </Box>
        </Modal>
    );
}

type Props = {
    openModal: boolean;
    children: React.ReactNode;
}

export default CustomModal;