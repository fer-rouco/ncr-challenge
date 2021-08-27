import styled from 'styled-components'

const StyledContainer = styled.div`
  border: 1px solid #DDD;
  border-radius: 25px;
  padding: 30px;
  background-color: #FFF;
  box-shadow: 7px 7px 3px rgb(0 0 0 / 50%);
  margin-top: 30px;

  &.small {
    width: 600px;
  }
  
  &.medium {
    width: 900px;
  }
  
  &.large {
    width: 1200px;
  }
  
`;

const StyledTitle = styled.h1`
  padding-left: 30px;
  padding-top: 5px;
  padding-bottom: 5px;
  margin-bottom: 30px;
  border: 1px solid #CCC;
  border-radius: 10px;
  background-color: #DED;
  color: lightseagreen; /* Antes: #097890; */
  box-shadow: 7px 7px 3px rgb(0 0 0 / 50%);
  font-family: cursive;
`;


// prop.size: "small", "medium", "large"
export default function Panel(props) {
  return (
    <StyledContainer className={"container panel-container " + props.size}>
      <div className="row justify-content-center">
        <div >
          <StyledTitle>{props.title}</StyledTitle>
          {props.children}
        </div>        
      </div>
    </StyledContainer>
  )
}
