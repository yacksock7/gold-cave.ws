import React from 'react';
import { useLocation, useNavigate, useParams, useSearchParams } from 'react-router-dom';

export const withRouter = Component => {
    const Wrapper = props => {
        const navigate = useNavigate();
        const params = useParams();
        const [searchParams] = useSearchParams();
        const location = useLocation();

        return <Component navigate={navigate} params={params} searchParams={searchParams} location={location} {...props} />;
    };

    return Wrapper;
};