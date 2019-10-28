import axios from 'axios';

export const DELETE_BINDR_SUCCESS = 'DELETE_BINDR_SUCCESS';
export const DELETE_BINDR_ERRORS = 'DELETE_BINDR_ERRORS';

export const deleteBindrSuccess = (deleteBindrId) => ({
    type: DELETE_BINDR_SUCCESS,
    payload: { deleteBindrId }
});

export const deleteBindrErrors = (deletedBindrErrors) => ({
    type: DELETE_BINDR_ERRORS,
    payload: { deletedBindrErrors }
});

export function deleteBindr(bindrIdToBeDeleted) {
    return (dispatch) => {
        return axios.delete("http://localhost:8080/bindrs/" + bindrIdToBeDeleted)
            .then(res => dispatch(deleteBindrSuccess(res.data)))
            .catch(err => {
                dispatch(deleteBindrErrors(err.response.data))
            })
    }
}