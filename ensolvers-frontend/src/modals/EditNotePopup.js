import React, { useState , useEffect} from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import {useNavigate} from 'react-router-dom'

const EditNotePopup = ({modal, toggle, updateTask, taskObj}) => {
    const [noteName, setNoteName] = useState('');
    const [description, setDescription] = useState('');
    const [noteCategory, setNoteCategory] = useState('');
    const [categoryList, setCategoryList] = useState([]);
    const navigate = useNavigate();

    const handleChange = (e) => {
        
        const {name, value} = e.target

        if(name === "noteName"){
            setNoteName(value)
        } else if(name=== "description"){
            setDescription(value)
        } else {
            setNoteCategory(value);
        }

    }

    const handleAddCategory = (e) =>{
        e.preventDefault()
        const noteCategoryTemp = noteCategory
        setCategoryList(categoryList.concat(noteCategoryTemp))
        setNoteCategory('')
    }

    const handleDeleteCategory = (index) => {
        let tempList = categoryList
        tempList.splice(index, 1)
        setCategoryList(tempList)
        navigate('/');
    }

    const setDefaultValues = () =>{
        setNoteName(taskObj.Name)
        setDescription(taskObj.Description)
        setCategoryList(taskObj.Categories)
    }

    useEffect(() => {
        setDefaultValues()
    },[])

    const handleUpdate = (e) => {
        e.preventDefault();
        let tempObj = {}
        tempObj['Name'] = noteName
        tempObj['Description'] = description
        tempObj["Categories"] = categoryList
        updateTask(tempObj)
    }

    const onCancel = () =>{
        setDefaultValues()
        toggle()
    }

    return (
        <Modal isOpen={modal} toggle={toggle}>
            <ModalHeader toggle={toggle}>Update Note</ModalHeader>
            <ModalBody>
            
                    <div className = "form-group" style={{marginTop: "20px"}}>
                        <label>Title</label>
                        <input type="text" className = "form-control" value = {noteName} onChange = {handleChange} name = "noteName"/>
                    </div>
                    <div className = "form-group" style={{marginTop: "20px"}}>
                        <label>Categories</label>
                        <div className='category-list'>
                            <ul>
                                {categoryList.map((category,index) => <div className='category-item'><li>{category}</li> <i class="fas fa-trash-alt" onClick = {() => handleDeleteCategory(index)}></i></div>)}
                            </ul>
                        </div> 
                    </div>
                    <div className = "form-group" style={{marginTop: "20px"}}>
                        <label>Add Category</label>
                        <div className='add-category'>
                            <input type="text" className = "form-control" value = {noteCategory} onChange = {handleChange} name = "category"/>
                            <Button color="primary" onClick={handleAddCategory}>Add</Button>
                        </div>
                    </div>
                    <div className = "form-group" style={{marginTop: "20px"}}>
                        <label>Description</label>
                        <textarea rows = "5" className = "form-control" value = {description} onChange = {handleChange} name = "description"></textarea>
                    </div>
                
            </ModalBody>
            <ModalFooter>
            <Button color="primary" onClick={handleUpdate}>Update</Button>{' '}
            <Button color="secondary" onClick={onCancel}>Cancel</Button>
            </ModalFooter>
      </Modal>
    );
};

export default EditNotePopup;