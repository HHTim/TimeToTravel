
init_tinymce = function () {
    
    tinymce.init({
      selector: 'textarea',
      height: 350,
      language: 'zh_TW',
    //   language: 'en_us',
      theme: 'modern',
      resize: false,
      style_formats: [
        {    title: 'Heading 4',    block: 'h4'  }, 
        {    title: 'Heading 5',    block: 'h5'  }, 
        {    title: 'Heading 6',    block: 'h6'  }, 
        {    title: 'Times New Roman',    inline: 'span',    styles: {      'font-family': 'Times New Roman'    }  }, 
        {    title: 'Arial',    inline: 'span',    styles: {      'font-family': 'Arial'    }  }, 
        {    title: 'Arial Black',    inline: 'span',    styles: {      'font-family': 'Arial Black'    }  }, 
        {    title: '8px',    inline: 'span',    styles: {      'font-size': '8px'    }  }, 
        {    title: '10px',    inline: 'span',    styles: {      'font-size': '10px'    }  }, 
        {    title: '12px',    inline: 'span',    styles: {      'font-size': '12px'    }  }, 
        {    title: '14px',    inline: 'span',    styles: {      'font-size': '14px'    }  }, 
        {    title: '18px',    inline: 'span',    styles: {      'font-size': '18px'    }  }, 
        {    title: '24px',    inline: 'span',    styles: {      'font-size': '24px'    }  }, 
        {    title: '36px',    inline: 'span',    styles: {      'font-size': '36px'    }  }, 
        {    title: 'Indent 2 em',    block: 'p',    styles: {      'text-indent': '2em'    }  },
        {    title: 'Float Right',    block: 'div',    styles: {      'float': 'right', 'margin-left': '1em', 'margin-bottom': '1em', 'clear': 'both'    }  },
    
        {    title: 'Float Left',    block: 'div',    styles: {      'float': 'left', 'margin-right': '1em', 'margin-bottom': '1em;', 'clear': 'both'    }  }
      ],
      plugins: [
          //   wordcount imagetools media image 不要圖片相關的 ， 或是 需要等 圖片部分OK 在客製化!!!!!!!!
        'advlist autolink lists link media image charmap print preview hr anchor pagebreak',
        'searchreplace visualblocks visualchars code fullscreen',
        'insertdatetime  nonbreaking save table contextmenu directionality',
        'emoticons template paste textcolor colorpicker textpattern  codesample toc imagetools media image'
      ],
      toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent',
      toolbar2: 'link image tableprops | print preview media | forecolor backcolor emoticons | codesample code ',
      image_advtab: true,
      /*
      templates: [{
        title: 'Test template 1',
        content: 'Test 1'
      }, {
        title: 'Test template 2',
        content: 'Test 2'
      }],
      */
      content_css: [
        '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
        // '//www.tinymce.com/css/codepen.min.css'
      ],
      setup: function(editor) {
            editor.on('change', function(e) {
                //console.log('change event', e);
                //tinymce.get('file_content').getContent();
                tinymce.triggerSave();
                // _update_preview_window();
               
            });
        }
    });
    
    }; 
    
    /**
     * 所有要開始啟動的功能
     */
    var _startup = function () {
        
        init_tinymce(); 
    };
    
    $(function () {
        _startup();
    });
    