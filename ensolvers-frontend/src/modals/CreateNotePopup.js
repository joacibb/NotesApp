import React, { useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import {useNavigate} from 'react-router-dom'

const CreateNotePopup = ({modal, toggle, save}) => {
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
        if(!categoryList.includes(noteCategoryTemp) && noteCategoryTemp !== ""){
            setCategoryList(categoryList.concat(noteCategoryTemp))
            setNoteCategory('')
        } else{
            alert("Repeated category. Please insert a new one");
        }
    }

    const handleDeleteCategory = (index) => {
        let tempList = categoryList
        tempList.splice(index, 1)
        setCategoryList(tempList)
        navigate('/');
    }

    const handleSave = (e) => {
        e.preventDefault()
        let noteObj = {}
        noteObj["Name"] = noteName
        noteObj["Description"] = description
        noteObj["Categories"] = categoryList
        save(noteObj)
        setNoteName('')
        setDescription('')
        setCategoryList([])
    }
    const setDefaultValues = () =>{
        setNoteName('')
        setDescription('')
        setCategoryList([])
    }

    const onCancel = () =>{
        setDefaultValues()
        toggle()
    }

    return (
        <Modal isOpen={modal} toggle={toggle}>
            <ModalHeader toggle={toggle}>Create new note</ModalHeader>
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
                        <label>Content</label>
                        <textarea rows = "5" className = "form-control" value = {description} onChange = {handleChange} name = "description"></textarea>
                    </div>
                
            </ModalBody>
            <ModalFooter>
            <Button color="primary" onClick={handleSave}>Create</Button>{' '}
            <Button color="secondary" onClick={onCancel}>Cancel</Button>
            </ModalFooter>
      </Modal>
    );
};

export default CreateNotePopup;