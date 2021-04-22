<?php

namespace App\Controller;

use App\Entity\Comments;
use App\Entity\Posts;
use App\Form\Posts1Type;
use App\Repository\PostsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#/**
 #* @Route("/postsB")
 #*/
class PostsController extends AbstractController
{
    /**
     * @Route("/postsB", name="posts_index", methods={"GET"})
     */
    public function index(PostsRepository $postsRepository): Response
    {
        return $this->render('posts/index.html.twig', [
            'posts' => $postsRepository->findAll(),
        ]);
    }
    /**
     * @Route("/postsF", name="posts_indexf", methods={"GET"})
     */
    public function indexf(PostsRepository $postsRepository): Response
    {
        return $this->render('postsF/index.html.twig', [
            'posts' => $postsRepository->findAll(),
        ]);
    }
    /**
     * @Route("/new", name="posts_new")
     */
    public function new(Request $request): Response
    {
        $post = new Posts();
        $form = $this->createForm(Posts1Type::class, $post);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form['image']->getData();
            $fileName = bin2hex(random_bytes(6)).'.'.$file->guessExtension();
            $file->move ($this->getParameter('images_directory'),$fileName);

            $post->setImage('upload/'.$fileName);



            $date= new \DateTime();
            $post->setDateP($date);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($post);
            $entityManager->flush();
            return $this->redirectToRoute('posts_index');
        }

        return $this->render('postsF/new.html.twig', [
            'post' => $post,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/show/{idp}", name="posts_show")
     */
    public function show(PostsRepository $repository,Posts $post,$idp): Response
    {
        $repository=$this->getDoctrine()->getRepository(Posts::class);

        $post=$repository->find($idp);
        return $this->render('posts/show.html.twig', [
            'post' => $post,
        ]);
    }
    /**
     * @Route("/show1/{idp}", name="posts_show1")
     */
    public function show1(PostsRepository $repository,Posts $post,$idp): Response
    {
        $repository=$this->getDoctrine()->getRepository(Posts::class);

        $post=$repository->find($idp);
        $post->setViews($post->getViews()+1);
        $repository1=$this->getDoctrine()->getRepository(Comments::class);
        $comments= $repository1->findBy(['Post'=>$post]);
        $this->getDoctrine()->getManager()->persist($post);
        $this->getDoctrine()->getManager()->flush();
        return $this->render('postsF/show.html.twig', [
            'post' => $post,
            'comment'=>$comments
        ]);
    }




    /**
     * @Route("/{idp}/edit", name="posts_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Posts $post): Response
    {
        $form = $this->createForm(Posts1Type::class, $post);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $file = $form['image']->getData();
            $fileName = bin2hex(random_bytes(6)).'.'.$file->guessExtension();
            $file->move ($this->getParameter('images_directory'),$fileName);

            $post->setImage('upload/'.$fileName);


            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('posts_index');
        }

        return $this->render('postsF/edit.html.twig', [
            'post' => $post,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idp}", name="posts_delete", methods={"POST"})
     */
    public function delete(Request $request, Posts $post): Response
    {
        if ($this->isCsrfTokenValid('delete'.$post->getIdp(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($post);
            $entityManager->flush();
        }

        return $this->redirectToRoute('posts_index');
    }
    /**
     * @Route ("/{idpone}"),name="one_post", methods={"GET"})
     */
    public  function showpost($id,Posts $post) : Response
    {
        $post = $this->getDoctrine()->getManager()->getRepository(Posts::class)->find($id);
        return $this->render('posts/show.html.twig', [
            'post' => $post,
        ]);
    }

    // *****************************      LIKES      ****************************************

    public  function like (Request $rq,$id){
        $like =new Likes();

        $posts = $this->getDoctrine()->getRepository(Posts::class)->find($id);
        $checkk = $this->getDoctrine()->getRepository(Likes::class)->check($id->getId());
        $em=$this->getDoctrine()->getManager();
        //    $likes=$em->getRepository(Likes::class)->find($id);

        if (empty($checkk)) {
            $like->setIdp($id);

            $posts->setLikes($posts->getLikes() + 1);
            $em->persist($like);
            $em->flush();

        } else {
            // $this->deleteLikespAction($rq, $like->setIdp());


            $like = $this->getDoctrine()->getRepository(Likes::class)->findOneBy(['idl'=>$checkk[0]]);
            $em->remove($like);

            $posts->setLikes($posts->getLikes() - 1);
            $em->flush();
        }


        return $this->redirectToRoute('posts_index');

    }
    public function deletelike (Request $rq,$id)
    {
        $ref = $rq->headers->get('referer');

        $em = $this->getDoctrine()->getManager();
        $like = $this->getDoctrine()->getRepository(Likes::class)->find($id);
        $em->remove($like);
        $em->flush();
        return $this->redirect($ref);

    }


////////**************************TRI*******************************************//////

    /**
     * @Route("/triP", name="triP")
     */

    public function TriP(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $query = $em->createQuery(
            'SELECT p FROM App\Entity\Posts p 
            ORDER BY p.dateP'
        );


        $rep = $query->getResult();

        return $this->render('PostsF/index.html.twig',
            array('post' => $rep));

    }



}
