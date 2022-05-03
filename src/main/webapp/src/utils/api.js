const BACKEND_API_URL = process.env.REACT_APP_BACKEND_API_URL

function formatParams(params) {
    return "?" + Object
        .keys(params)
        .map(function (key) {
            return key + "=" + encodeURIComponent(params[key])
        })
        .join("&")
}

function sendRequest(method, endpoint, callback, data, dataAsParams = false, paramsData = null) {
    let jsonData;
    let params = "";
    if (!dataAsParams && method === "POST" && data) {
        jsonData = JSON.stringify(data);
    }
    if (dataAsParams || method === "GET") {
        params = formatParams(data);
    }
    if (paramsData) {
        params = formatParams(paramsData);
    }

    const xhr = new XMLHttpRequest();
    const url = BACKEND_API_URL + endpoint;
    const authToken = localStorage.getItem("accessToken");

    xhr.responseType = "json";
    xhr.open(method, url + params);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    if (authToken)
        xhr.setRequestHeader("Authorization", "Bearer " + authToken);
    xhr.onload = () => {
        callback(xhr.response, xhr.status);
    }
    xhr.onerror = (e) => {
        console.log(e);
        callback({ "message": "Error request" }, 400);
    }
    xhr.send(jsonData);
}

export function registerUser(data, callback) {
    sendRequest("POST", `/api/auth/signup`, callback, data);
}

export function loginUser(data, callback) {
    sendRequest("POST", `/api/auth/signin`, callback, data);
}

export function loadUserInfo(callback) {
    sendRequest("GET", `/api/user/profile/personal`, callback, {});
}

export function loadUserActiveBookings(callback) {
    sendRequest("GET", `/api/reservation/profile/active`, callback, {});
}

export function loadUserPastBookings(callback) {
    sendRequest("GET", `/api/reservation/profile/past`, callback, {});
}

export function loadUserFutureBookings(callback) {
    sendRequest("GET", `/api/reservation/profile/future`, callback, {});
}

export function searchRooms(data, callback) {
    sendRequest("POST", `/api/search-rooms`, callback, data);
}

export function cancelBooking(id, callback) {
    sendRequest("DELETE", `/api/reservation/${id}/cancel`, callback, {});
}

export function makeBooking(data, callback) {
    sendRequest("POST", `/api/reservation/create`, callback, data);
}

export function loadEmployees(callback) {
    sendRequest("GET", `/api/employee/list`, callback, {});
}

export function updateEmployee(data, callback, params) {
    sendRequest("POST", `/api/employee/update` + params, callback, data);
}

export function loadReservations(callback) {
    sendRequest("GET", `/api/assignment/all-reservations/`, callback, {});
}

export function loadReservationRooms(reservationId, callback) {
    sendRequest("GET", `/api/assignment/assigned-rooms`, callback, { reservationId: reservationId });
}

export function createAssignment(data, callback) {
    sendRequest("POST", `/api/assignment/create`, callback, data, true);
}

export function deleteAssignment(data, callback) {
    sendRequest("DELETE", `/api/assignment/delete`, callback, data, true);
}

export function loadAvailableRooms(reservationId, callback) {
    sendRequest("GET", `/api/assignment/available-rooms`, callback, { reservationId: reservationId });
}

export function loadSeasons(callback) {
    sendRequest("GET", `/api/season/get-my-seasons`, callback, {});
}

export function cancelSeason(seasonName, callback) {
    sendRequest("DELETE", `/api/season/delete`, callback, { seasonName: seasonName }, true);
}

export function loadRoomTypes(callback) {
    sendRequest("GET", `/api/season/get-room-types`, callback, {});
}

export function createSeason(data, params, callback) {
    sendRequest("POST", `/api/season/create`, callback, data, false, params);
}

export function loadSeasonPrices(seasonName, callback) {
    sendRequest("GET", `/api/season/get-season-price`, callback, { seasonName: seasonName }, true);
}

export function loadAnnouncements(callback) {
    sendRequest("GET", `/api/announcements/get`, callback, {});
}

export function loadHotels(callback) {
    sendRequest("GET", `/api/hotels/list`, callback, {});
}

export function loadHotelRoomTypes(hotelId, callback) {
    sendRequest("GET", `/api/hotel/roomtypes`, callback, { id: hotelId }, true);
}
